package com.local.study.config.redis;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/8/26 4:15 下午
 */
//@Configuration
//@ConditionalOnClass(RedisOperations.class)
public class RedisConfig {

    @Autowired
    RedisProperties redisProperties;

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisJsonSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//		objectMapper.registerModule(new JavaTimeModule());
//      objectMapper.registerModule((new SimpleModule()).addSerializer(new GenericJackson2JsonRedisSerializer.NullValueSerializer((String)null)));
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    @Bean
    public <T> RedisTemplate<String, T> redisTemplate(@Qualifier("lettuceConnectionFactory") RedisConnectionFactory factory) {
        RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
        // GenericJackson2JsonRedisSerializer会序列化对象的类型
        // 因此在重构时需要留意对象的改动
        redisTemplate.setDefaultSerializer(genericJackson2JsonRedisJsonSerializer());
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setHashKeySerializer(stringRedisSerializer());
        redisTemplate.setConnectionFactory(factory);

        return redisTemplate;
    }

    /**
     * 构建Redis连接池
     * <p>
     * maxIdle  最大空闲连接数 推荐20
     * mixIdle  最小空闲连接数 推荐10
     * maxTotal 设置最大连接数，（根据并发请求合理设置）推荐100。
     *
     * @return 返回一个连接池
     */
    private GenericObjectPoolConfig genericObjectPoolConfig(RedisProperties.Pool pool) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(pool.getMaxIdle());
        config.setMinIdle(pool.getMinIdle());
        config.setMaxTotal(pool.getMaxActive());

        if (pool.getMaxWait() != null) {
            config.setMaxWaitMillis(pool.getMaxWait().toMillis());
        }
        return config;
    }


    @Bean(name = "lettuceConnectionFactory", destroyMethod = "destroy")
    public LettuceConnectionFactory lettuceConnectionFactory() {

        //开启 自适应集群拓扑刷新和周期拓扑刷新
        ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                // 开启全部自适应刷新
                .enableAllAdaptiveRefreshTriggers() // 开启自适应刷新,自适应刷新不开启,Redis集群变更时将会导致连接异常
                // 自适应刷新超时时间(默认30秒)
                .adaptiveRefreshTriggersTimeout(Duration.ofSeconds(10)) //默认关闭开启后时间为30秒
                // 开周期刷新
                // 默认关闭开启后时间为60秒 ClusterTopologyRefreshOptions.DEFAULT_REFRESH_PERIOD 60  .enablePeriodicRefresh(Duration.ofSeconds(2)) = .enablePeriodicRefresh().refreshPeriod(Duration.ofSeconds(2))
                .enablePeriodicRefresh(Duration.ofSeconds(15)) //每隔15秒回刷新
                .build();

        //https://github.com/lettuce-io/lettuce-core/wiki/Client-Options
        ClientOptions clientOptions = ClusterClientOptions.builder()
                .topologyRefreshOptions(clusterTopologyRefreshOptions)
                .build();

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .poolConfig(genericObjectPoolConfig(redisProperties.getLettuce().getPool()))
                //.readFrom(ReadFrom.MASTER_PREFERRED)
                .clientOptions(clientOptions)
                .commandTimeout(redisProperties.getTimeout()) //默认RedisURI.DEFAULT_TIMEOUT 60
                .build();

        List<String> clusterNodes = redisProperties.getCluster().getNodes();
        Set<RedisNode> nodes = new HashSet<RedisNode>();
        clusterNodes.forEach(address -> nodes.add(new RedisNode(address.split(":")[0].trim(), Integer.parseInt(address.split(":")[1]))));

        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        clusterConfiguration.setClusterNodes(nodes);

        if (!StringUtils.isEmpty(redisProperties.getPassword())) {
            clusterConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        clusterConfiguration.setMaxRedirects(3);
        if (redisProperties.getCluster().getMaxRedirects() != null && redisProperties.getCluster().getMaxRedirects() > 0) {
            clusterConfiguration.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());
        }

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(clusterConfiguration, clientConfig);
        // 是否允许多个线程操作共用同一个缓存连接，默认true，false时每个操作都将开辟新的连接
        // lettuceConnectionFactory.setShareNativeConnection(false);
        // 重置底层共享连接, 在接下来的访问时初始化
        // lettuceConnectionFactory.resetConnection();
        return lettuceConnectionFactory;


    }
}

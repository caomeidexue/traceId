package com.local.study.java8.springjava8;

import com.local.study.java8.springjava8.model.Document;
import com.local.study.java8.springjava8.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/17 11:17 上午
 */
public class FunctionTest {


    public static void main(String[] args) {
        DocumentConsumer consumer = addToLoaded(false);
        List<Document> loaded = new ArrayList<>();
        loaded.add(new Document());
        loaded.add(new Document());
        Profile profile = new Profile();
        loaded.forEach(document -> consumer.accept(profile,document));
        System.out.println("222");

    }


    @FunctionalInterface
    private interface DocumentConsumer {

        void accept(Profile profile, Document document);

    }


    private static DocumentConsumer addToLoaded(boolean checkForExisting) {
        DocumentConsumer documentConsumer = (profile, document) -> {
            if (checkForExisting) {
                System.out.println(1111);
            }
        };
        return documentConsumer;
    }
}

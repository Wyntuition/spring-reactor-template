package com.excella.reactor;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.core.publisher.Flux;

public class ReactorApplicationTests {

	private static List<String> words = Arrays.asList(
        "the",
        "quick",
        "brown",
        "fox",
        "jumped",
        "over",
        "the",
        "lazy",
        "dog"
				);
				
	@Test
	public void findingMissingLetter() {
		Flux<String> manyLetters = Flux
					.fromIterable(words)
					.flatMap(word -> Flux.fromArray(word.split("")))
					.distinct()
					.sort()
					.zipWith(Flux.range(1, Integer.MAX_VALUE),
								(string, count) -> String.format("%2d. %s", count, string));
	
		manyLetters.subscribe(System.out::println);
	}

}

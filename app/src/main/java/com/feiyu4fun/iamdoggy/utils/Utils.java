package com.feiyu4fun.iamdoggy.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.feiyu4fun.iamdoggy.interfaces.management.Possibililty;

public class Utils {
	private Utils() {}
	public static <T extends Possibililty> int getRandomPossibility(List<T> probabilities) {
		int index = -1;
		if (CollectionUtils.isEmpty(probabilities)) {
			return index;
		}
		
		double sum = 0;
		for (Possibililty probability : probabilities) {
			sum += probability.getPossibility();
		}
		
		if (sum <= 0) {
			return index;
		}
		
		double tempSum = 0d;
		List<Double> sortProbabilityList = new ArrayList<Double>(probabilities.size());
		for (Possibililty probability : probabilities) {
			tempSum += probability.getPossibility();
			sortProbabilityList.add(tempSum/sum);
		}
		
		double randomDouble = Math.random();
		sortProbabilityList.add(randomDouble);
		
		Collections.sort(sortProbabilityList);
		index = sortProbabilityList.indexOf(randomDouble);
		
		return index;
	}
}

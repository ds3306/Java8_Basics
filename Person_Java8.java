package com.javaBasics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Person_Java8 {
	public static void main(String args[]) {
		List<EPerson> employeeList = new ArrayList<>();
		employeeList.add(new EPerson("Alex", 23, 23000, "USA"));
		employeeList.add(new EPerson("Alex", 52, 23000, "USA"));
		employeeList.add(new EPerson("Ben", 63, 25000, "China"));
		employeeList.add(new EPerson("Dave", 34, 56000, "India"));
		employeeList.add(new EPerson("Zodi", 43, 67000, "USA"));
		employeeList.add(new EPerson("Ryan", 53, 54000, "China"));
		
		
		Collections.sort(employeeList , Comparator.comparing(EPerson::getAge));
		System.out.println("sorted employeeList "+ employeeList);
		
		Collections.sort(employeeList , Comparator.comparing(EPerson::getName).thenComparing(EPerson::getAge));
		
		System.out.println("sorted employeeList ,if name is same then sort based on age"+ employeeList);

		// The employees are grouped by country using the groupingBy() method.
		Map<String, List<EPerson>> employeeMapList = employeeList.stream()
				.collect(Collectors.groupingBy(EPerson::getCountry));

		Map<String, Set<EPerson>> employeeMapSet = employeeList.stream()
				.collect(Collectors.groupingBy(EPerson::getCountry, Collectors.toSet()));

		Map<String, Map<Integer, List<EPerson>>> employeeMapListGrpBy = employeeList.stream()
				.collect(Collectors.groupingBy(EPerson::getCountry, Collectors.groupingBy(EPerson::getAge)));

		Map<String, Integer> employeeMapKV = employeeList.stream()
				.collect(Collectors.groupingBy(EPerson::getCountry, Collectors.summingInt(EPerson::getSalary)));

		Map<String, Optional<EPerson>> employeeMapMax = employeeList.stream().collect(Collectors
				.groupingBy(EPerson::getCountry, Collectors.maxBy(Comparator.comparingInt(EPerson::getSalary))));

		Map<Boolean, List<EPerson>> employeeMapPartition = employeeList.stream()
				.collect(Collectors.partitioningBy(emp -> emp.getAge() > 30));

		System.out.println(employeeMapPartition);

		System.out.println(employeeMapList);
		System.out.println(employeeMapSet);
		System.out.println(employeeMapListGrpBy);
		System.out.println(employeeMapKV);
		System.out.println(employeeMapMax);
		System.out.println(employeeMapPartition);

		System.out.println("------------------------------- Serial Stream  ---------------------------------");
		Stream.of(1, 2, 3, 4, 5, 6, 7).forEach(num -> System.out.println(num + " " + Thread.currentThread().getName()));

		System.out.println("-------------------------------- Parallel Stream -----------------------------");
		Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14).parallel()
				.forEach(num -> System.out.println(num + " " + Thread.currentThread().getName()));
		
		Map<String, Integer> fruits = new HashMap<>();
		fruits.put("apple", 20);

		Integer val = fruits.computeIfPresent("apple", (k, v) -> v + 10);

		System.out.println(val);

		val = fruits.computeIfAbsent("banana", v -> 10);

		System.out.println(val);

	}
}

class EPerson {
	String name;
	int age;
	int salary;
	String country;

	EPerson(String name, int age, int salary, String country) {
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getSalary() {
		return salary;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + salary;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EPerson other = (EPerson) obj;
		if (age != other.age)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee{" + "name='" + name + '\'' + ", age=" + age + ", salary=" + salary + '}';
	}
}

package com.yashv.practice;

import java.util.*;

class Person {
	String name;
	int id;

	public Person(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	/* 
	 * HashSet's internal HashMap not only uses equals method of the
	 * containing objects but also checks if the hash codes of the objects match,
	 * therefore its necessary to override both to treat objects as equal in the Set.
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Person person = (Person) obj;
		return name.equals(person.name);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class PersonComparator implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
		return Integer.compare(p1.id, p2.id);
	}
}

public class InconsitentTreeSet {
	public static void main(String[] args) {
		Set<Person> peopleTreeSet = new TreeSet<>(new PersonComparator()), peopleHashSet = new HashSet<>();
		Person p1 = new Person("Alice", 1),
				p2 = new Person("Bob", 2),
				p3 = new Person("Alice", 3); // Different id but same name
		System.out.println("p1 equals p3? " + p1.equals(p3));
		
		peopleTreeSet.add(p1);
		peopleHashSet.add(p1);

		peopleTreeSet.add(p2);
		peopleHashSet.add(p2);

		peopleTreeSet.add(p3);
		peopleHashSet.add(p3);

		System.out.println("Elements in TreeSet: " + peopleTreeSet.size()
			+ ", Elements in HashSet: " + peopleHashSet.size());
	}
}

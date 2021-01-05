package kh.java.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import kh.java.collection.set.Person;

/**
 * Map=Collection의 하위인터페이스가 아니라 collection으로 제어불가
 * key, Value 한쌍으로 요소를 구성된다.
 * key를 통해서 value를 접근해 사용
 * 
 * key는 중복을 허용하지 않는다!
 * value는 중복되어도 좋다.
 * 
 * 동일한 key로 추가저장하면, 나중 value로 치환된다.(덮어씌워진다)
 *
 */
public class HashMapTest {
	public static void main(String[] args) {
		HashMapTest h = new HashMapTest();
		h.test2();
	}
	
	public void test1() {
		//<Key,Value>
		//key로 Integer만 허용, Value로 String만 허용한다는 제네릭 완성
		HashMap<Integer, String> map1 = new HashMap<>();
		Map<Character,Person> map2 = new HashMap<>();
		
		//요소 추가
		map1.put(1, "홍길동");
		map1.put(2, "신사임당");
		map1.put(3, "세종대왕");
		
		//요소 가져오기
		String s1 = map1.get(1);
		System.out.println(s1);
		
		System.out.println(map1);
		
		//동일한 키값으로 요소 추가
		//키값이 동일하다면 value값은 덮어씌워짐
		map1.put(2, "장영실");
		System.out.println(map1);
		
		
		//map2에 요소추가
		map2.put('a', new Person("Aida"));
		map2.put('b', new Person("Bob"));
		map2.put('c', new Person("Clain"));
		map2.put('d', new Person("David"));
		System.out.println(map2);
		
		//특정 key가 존재하는가?
		System.out.println(map2.containsKey('b'));//char -> new Character
		System.out.println(map2.containsKey('x'));
		
		//특정 value가 존재하는가? (Person 클래스 equals, hashcode 재정의해서 가능)
		System.out.println(map2.containsValue(new Person("David")));
		
		
		Person p1 = map2.get('b');
		System.out.println(p1+", "+p1.hashCode());
		map2.put('b', new Person("Bill")); //키가 같다면 덮어씌기된 값으로 가져옴
		p1=map2.get('b');
		System.out.println(p1+", "+p1.hashCode());
		
	}
	/**
	 * 전체 요소 열람하기
	 * 요소를 열람하려면 Set객체에 담아서 접근을 모두 해야하는데 방법은 두가지
	 * 1. key값을 set에 담기 (keySet)
	 * 2. key, vlaue한쌍을 set에 담기 (EntrySet)
	 */
	public void test2() {
		Map<String, Person> map = new HashMap<>();
		map.put("honggd", new Person("홍길동"));
		map.put("sinsa", new Person("신사임당"));
		map.put("sejong", new Person("세종대왕"));
		map.put("jys", new Person("장영실"));
		
		//1. keySet : key집합
		Set<String> keySet = map.keySet();
		//keySet + forEach
		System.out.println("====== keySet + ForEach ======");
		for(String key : keySet) {
			Person value = map.get(key);
			System.out.printf("key = %s, value = %s%n", key, value);
		}
		System.out.println();
		
		//keySet + iterator
		System.out.println("====== keySet + Iterator ======");
		Iterator<String> iter = keySet.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Person value = map.get(key);
			System.out.printf("key = %s, value = %s%n", key, value);
		}
		System.out.println();
		
		//2. Map.Entry(key,value한쌍)Set
		Set<Entry<String, Person>> entrySet = map.entrySet();
		//entrySet + forEach
		System.out.println("====== entrySet + ForEach ======");
		for(Entry<String,Person> entry : entrySet) {
			String key = entry.getKey();
			Person value = entry.getValue();
			System.out.println("key : "+key+", value : "+value);
		}
		System.out.println();
		
		//entrySet + iterator
		System.out.println("====== entrySet + Iterator ======");
		Iterator<Entry<String,Person>> it = entrySet.iterator();
		while(it.hasNext()) {
			Entry<String,Person> entry= it.next();
			String key = entry.getKey();
			Person value = entry.getValue();
			System.out.println("key : "+key+", value : "+value);
		}
	}
}

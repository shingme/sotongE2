package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// 상담했던 데이터들을 이용해 비즈니스로직을 짰다. --> 상담한 데이터 기반으로 해당 카테고리에 많이 상담을 해본 교수님을 추천해준다. (많이 상담해본 교수님은 그 분야에서 잘 상담할 거라는 기반으로)
public class WeightCaculate{
	

	//교수 백분율을 구한 값과 학생이 가중치 설정한 값 합산하기!!
	public Map<String, HashMap<String,Integer>> profAndStuOfWeightSum(Map<String, HashMap<String,Integer>> profConsultCnt, List<WeightDTO> myWeight, int totalCount){
		
		for(int i=0; i<myWeight.size(); i++){
			Iterator<String> mapIter = profConsultCnt.keySet().iterator();
			
			while(mapIter.hasNext()){
				String key = mapIter.next();
		        HashMap<String, Integer> hashIter = profConsultCnt.get(key);
		        Iterator<String> categoryKey = hashIter.keySet().iterator();	
		        while(categoryKey.hasNext()){
		        	String category = categoryKey.next();

		        	if(myWeight.get(i).getCategory().equals(category)){
		        		//교수의 백분율 구하기 
		        		double calcuValue = Math.round((hashIter.get(category)/(double)totalCount)*100);
			        	//백분율 + 가중치를 다시 해쉬맴에 저장
		        		hashIter.put(category, ((int)Math.ceil(calcuValue))*myWeight.get(i).getWeight());
		        	}
		        }
		     }			
		}
		System.out.println("백분율 계산// : " + profConsultCnt);
		return profConsultCnt;
	}
	
	//profConsultCnt에 들어있는 각각의 교수의 카테고리를 더한다.(한명의 교수가 한개의 점수를 가질 수 있도록!--> 서로 비교할 수 있게 합한다.)
	public List<String> profEachCategoryCntSum(Map<String, HashMap<String,Integer>> profConsultCnt){
		
		HashMap<String, Integer> resultProfConsultValue = new HashMap<String, Integer>();
		Iterator<String> mapIter = profConsultCnt.keySet().iterator();
		int total;
		while(mapIter.hasNext()){
			String key = mapIter.next(); //합산해서 다시 해쉬맵에 넣자!!
			HashMap<String, Integer> hashIter = profConsultCnt.get(key);
			Iterator<String> categoryKey = hashIter.keySet().iterator();
			total = 0;
			while(categoryKey.hasNext()){
				String category = categoryKey.next();
				total += hashIter.get(category); 
			}
			
			resultProfConsultValue.put(key, total);
		}
		System.out.println(resultProfConsultValue);
	
		return sortByValue(resultProfConsultValue);
		
	}
	
	//해쉬맵은 순서가 없기 때문에 정렬 못하므로 순서가 있는 리스트에 넣고 정렬한다!
	/////////동점일 때랑 3순위까지만 구하기!!
	public static List<String> sortByValue(HashMap<String, Integer> profConsultCnt){
		 
		List<String> list = new ArrayList<String>();

		 Iterator<String> iter = profConsultCnt.keySet().iterator();
		 
		 while(iter.hasNext()){
			 
			 String profNo = iter.next();
			 
			 if(profConsultCnt.get(profNo) == 0){
				 profConsultCnt.remove(profNo);
				 //값을 삭제하고 iterator()를 다시 생성해줘야 조건에 맞는 값들을 모두 삭제할 수 있다.
				 iter = profConsultCnt.keySet().iterator();
			 }
		 }

		 list.addAll(profConsultCnt.keySet()); // key목록을 리스트에 담는다.
	     
	     Collections.sort(list,new Comparator<Object>(){
	            @Override 
	            public int compare(Object o1,Object o2){ //맵이어서 오브젝트로 함
	                //v1와 v2값들이 서로 비교해서 정렬된다.
	            	Object v1 = profConsultCnt.get(o1);
	                Object v2 = profConsultCnt.get(o2);
	            
	                return ((Comparable) v1).compareTo(v2); // 비교해서 v1>v2면 양수(1), v1=v2이면 0, v1<v2이면 음수(-1)
	            }											// 양수 :v2뒤에 v1을 둔다.	음수 : v2앞에 v1을 둔다.
	        });
	     	//역순으로 만들어주기 때문에 뒤집어줘야 한다.
	        Collections.reverse(list);
	        
	        if(list.size() > 3){
	        	for(int i = 3; i<list.size(); i++){
	        		list.remove(i);
	        	}
	        }
	        return list;
	}
}

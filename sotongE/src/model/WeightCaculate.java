package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// ����ߴ� �����͵��� �̿��� ����Ͻ������� ®��. --> ����� ������ ������� �ش� ī�װ��� ���� ����� �غ� �������� ��õ���ش�. (���� ����غ� �������� �� �о߿��� �� ����� �Ŷ�� �������)
public class WeightCaculate{
	

	//���� ������� ���� ���� �л��� ����ġ ������ �� �ջ��ϱ�!!
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
		        		//������ ����� ���ϱ� 
		        		double calcuValue = Math.round((hashIter.get(category)/(double)totalCount)*100);
			        	//����� + ����ġ�� �ٽ� �ؽ��ɿ� ����
		        		hashIter.put(category, ((int)Math.ceil(calcuValue))*myWeight.get(i).getWeight());
		        	}
		        }
		     }			
		}
		System.out.println("����� ���// : " + profConsultCnt);
		return profConsultCnt;
	}
	
	//profConsultCnt�� ����ִ� ������ ������ ī�װ��� ���Ѵ�.(�Ѹ��� ������ �Ѱ��� ������ ���� �� �ֵ���!--> ���� ���� �� �ְ� ���Ѵ�.)
	public List<String> profEachCategoryCntSum(Map<String, HashMap<String,Integer>> profConsultCnt){
		
		HashMap<String, Integer> resultProfConsultValue = new HashMap<String, Integer>();
		Iterator<String> mapIter = profConsultCnt.keySet().iterator();
		int total;
		while(mapIter.hasNext()){
			String key = mapIter.next(); //�ջ��ؼ� �ٽ� �ؽ��ʿ� ����!!
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
	
	//�ؽ����� ������ ���� ������ ���� ���ϹǷ� ������ �ִ� ����Ʈ�� �ְ� �����Ѵ�!
	/////////������ ���� 3���������� ���ϱ�!!
	public static List<String> sortByValue(HashMap<String, Integer> profConsultCnt){
		 
		List<String> list = new ArrayList<String>();

		 Iterator<String> iter = profConsultCnt.keySet().iterator();
		 
		 while(iter.hasNext()){
			 
			 String profNo = iter.next();
			 
			 if(profConsultCnt.get(profNo) == 0){
				 profConsultCnt.remove(profNo);
				 //���� �����ϰ� iterator()�� �ٽ� ��������� ���ǿ� �´� ������ ��� ������ �� �ִ�.
				 iter = profConsultCnt.keySet().iterator();
			 }
		 }

		 list.addAll(profConsultCnt.keySet()); // key����� ����Ʈ�� ��´�.
	     
	     Collections.sort(list,new Comparator<Object>(){
	            @Override 
	            public int compare(Object o1,Object o2){ //���̾ ������Ʈ�� ��
	                //v1�� v2������ ���� ���ؼ� ���ĵȴ�.
	            	Object v1 = profConsultCnt.get(o1);
	                Object v2 = profConsultCnt.get(o2);
	            
	                return ((Comparable) v1).compareTo(v2); // ���ؼ� v1>v2�� ���(1), v1=v2�̸� 0, v1<v2�̸� ����(-1)
	            }											// ��� :v2�ڿ� v1�� �д�.	���� : v2�տ� v1�� �д�.
	        });
	     	//�������� ������ֱ� ������ ��������� �Ѵ�.
	        Collections.reverse(list);
	        
	        if(list.size() > 3){
	        	for(int i = 3; i<list.size(); i++){
	        		list.remove(i);
	        	}
	        }
	        return list;
	}
}

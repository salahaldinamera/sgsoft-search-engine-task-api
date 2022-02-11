package com.sgsoft.search_engine_task_api.services;

import com.sgsoft.search_engine_task_api.models.Order;
import com.sgsoft.search_engine_task_api.models.Resource;
import com.sgsoft.search_engine_task_api.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchService {

    @Autowired
    private ResourceRepository resourceRepository;

    /**
     *  This method search resources based on keyword and return matches
     * @param keyword The keyword to be searched
     * @param type The type of search ("Resource","Page","Image")
     * @return Returns resources that have tags matching the keyword
     */
    public List<Resource> search(String keyword, String type){
        // Convert keyword to lower case then separate by spaces
        keyword = keyword.toLowerCase();
        String[] keywords = keyword.split(" ");

        // Create has map to order the results (hashmap for ease of access by id)
        HashMap<Integer, Order> rankHashMap = new HashMap<>();

        for(int i = 0;i<keywords.length;i++){
            List<Resource> resources;

            // Check type
            if(type.contains("resource")){
                resources = resourceRepository.getResourcesByKeyword("%"+keywords[i]+"%");
            }else {
                resources = resourceRepository.getResourcesByKeywordAndType("%"+keywords[i]+"%",type);
            }

            // Loop through resources to set the priority based on number of tag matches
            for(int j=0;j<resources.size();j++){
                if(rankHashMap.containsKey(resources.get(j).getId())){
                    rankHashMap.get(resources.get(j)).incrementCount(1);
                }else {
                    rankHashMap.put(resources.get(j).getId(),new Order(1,resources.get(j)));
                }
            }
        }

        // Get the values from the hashmap
        Collection<Order> values = rankHashMap.values();

        // Store values in arraylist and sort them
        ArrayList<Order> orderArrayList = new ArrayList<>(values);
        Collections.sort(orderArrayList, Collections.reverseOrder());

        // Get the resources objects from order objects and store them in a list
        List<Resource> resources = new ArrayList<>();
        for(int i = 0;i < orderArrayList.size();i++){
            resources.add(orderArrayList.get(i).getResource());
        }

        return resources;
    }
}


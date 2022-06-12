package ch12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(int n, String[] plans, String[] clients) {
        int[] answer = new int[clients.length];
        Map<Integer, Map<Integer, Boolean>> map = new HashMap<>();
        Map<Integer, List<Integer>> clientsMap = new HashMap<>();
        List<Integer> dataList = new ArrayList<>();
        Map<Integer, Boolean> prev = new HashMap<>();
        for (int m = 0; m < plans.length; m++) {
            String[] split = plans[m].split(" ");
            int data = Integer.parseInt(split[0]);
            dataList.add(data);
            map.put(data, new HashMap<>(prev));
            for (int i = 1; i < split.length; i++) {
                map.get(data).put(Integer.parseInt(split[i]), true);
            }
            prev = new HashMap<>(map.get(data));
        }
        for (int c = 0; c < clients.length; c++) {
            String[] split = clients[c].split(" ");
            int data = Integer.parseInt(split[0]);
            clientsMap.put(data, new ArrayList<>());
            for (int i = 1; i < split.length; i++) {
                clientsMap.get(data).add(Integer.parseInt(split[i]));
            }
            int idx = -1;
            for (int i = 0; i < dataList.size(); i++) {
                int dt = dataList.get(i);
                if (data > dataList.get(i)) continue;
                if (map.get(dt).size() < clientsMap.get(data).size()) continue;
                boolean check = true;
                for (int serviceNumber : clientsMap.get(data)) {
                    //해당 부가서비스를 지원하지 않으면 이용할 수 없다.
                    if (!map.get(dt).containsKey(serviceNumber)) check = false;
                }
                if (check) {
                    idx = i;
                    break;
                }
            }
            if (idx == -1) answer[c] = 0;
            else answer[c] = idx + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 5;
        String[] plans = {
                "38 2 3","394 1 4"
        };
        String[] clients = {
                "10 2 3", "300 1 2 3 4", "500 1"
        };
        int[] solution = s.solution(n, plans, clients);
        for (int i : solution) {
            System.out.println(i);
        }

    }
}
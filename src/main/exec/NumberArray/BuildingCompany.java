package exec.NumberArray;


import java.util.*;

public class BuildingCompany {

    private Map<Integer, Integer> currentResources;
    private List<Project> waitList;

    static class Project {
        private Map<Integer, Integer> requirement;
        private Map<Integer, Integer> resources;
    }


    public static void main(String[] args) {
        BuildingCompany company = new BuildingCompany();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        company.currentResources = initResource(line);
        line = scanner.nextLine();
        int projectCount = Integer.valueOf(line);
        company.waitList = new ArrayList<>(projectCount);
        for (int i = 0; i < projectCount; i++) {
            String requireLine = scanner.nextLine();
            String offLine = scanner.nextLine();
            Project project = initProject(requireLine, offLine);
            company.waitList.add(project);
        }
        int result = company.maxProject();
        System.out.println(result);
    }

    private int maxProject() {
        int count = 0;
        Set<Project> waitSet = new HashSet<>(waitList.size());
        waitSet.addAll(waitList);
        int waitCount = waitSet.size();
        int cannotCount = 0;
        while (waitCount != cannotCount && !waitSet.isEmpty()) {
            waitCount = waitSet.size();
            cannotCount = 0;
            for (Project project : waitList) {
                if (waitSet.contains(project) && meetRequire(project)) {
                    count++;
                    addResource(project);
                    waitSet.remove(project);
                } else {
                    cannotCount++;
                }
            }
        }
        return count;
    }

    private void addResource(Project project) {
        for (Map.Entry<Integer, Integer> entry : project.resources.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (!currentResources.containsKey(key)) {
                currentResources.put(key, value);
            } else {
                currentResources.put(key, currentResources.get(key) + value);
            }
        }
    }

    private boolean meetRequire(Project project) {
        for (Map.Entry<Integer, Integer> entry : project.requirement.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (!currentResources.containsKey(key) || currentResources.get(key) < value) {
                return false;
            }
        }
        return true;
    }

    private static Project initProject(String requireLine, String offLine) {
        Map<Integer, Integer> requirement = initResource(requireLine);
        Map<Integer, Integer> resources = initResource(offLine);
        Project project = new Project();
        project.requirement = requirement;
        project.resources = resources;
        return project;
    }

    private static Map<Integer, Integer> initResource(String line) {
        String[] array = line.split(" ");
        int length = Integer.parseInt(array[0]);
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < length; i++) {
            result.put(Integer.valueOf(array[i + 1]), Integer.valueOf(array[i + 2]));
        }
        return result;
    }

}

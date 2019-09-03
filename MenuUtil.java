package com.jzqh.account;

import java.util.*;

/**
 * @author Tongch
 * @version 1.0
 * @time 2018/8/28 13:38
 */
public class MenuUtil {
    
    
    /**
     * 对转入的user
     *
     * @param menuSet
     * @return
     */
    public static MenuBo buildTree(Set<Authority> menuSet) {
        List<String> urlList = new ArrayList<>();
        for (Authority authority : menuSet) {
            urlList.add(authority.getPath());
        }
        if (urlList != null && urlList.size() > 0) {
            for (int i = 0; i < urlList.size(); i++) {
                if (urlList.get(i).toLowerCase().contains("user")) {//去除所有路径带有user的url
                    urlList.remove(i);
                }
            }
        }
        MenuBo menuBo1 = urlToMenu(urlList, menuSet);
        return menuBo1;
    }
    
    /**
     * 将url转成menubo
     *
     * @param urlList
     * @return
     */
    public static MenuBo urlToMenu(List<String> urlList, Set<Authority> menuSet) {
        Map<String, List<String[]>> mapByLength = getMapByLength(getAllUrlArray(urlList));
        MenuBo menuBo1 = buildRootNode(getFirstNode(urlList), mapByLength, menuSet);
        return menuBo1;
        
    }
    
    /**
     * 得到分割后的list string数组
     *
     * @param urlList
     * @return
     */
    public static List<String[]> getAllUrlArray(List<String> urlList) {
        List<String[]> allPathArray = new ArrayList<>();
        for (int i = 0; i < urlList.size(); i++) {
            String[] pathArray = strToArray(urlList.get(i));
            allPathArray.add(pathArray);//存放所有的分解后的路径
        }
        return allPathArray;
    }
    
    /**
     * 得到所有数组的头结点
     *
     * @param urlList
     * @return
     */
    public static TreeSet<String> getFirstNode(List<String> urlList) {
        TreeSet<String> firstNode = new TreeSet<>();
        for (int i = 0; i < urlList.size(); i++) {
            String[] pathArray = strToArray(urlList.get(i));
            firstNode.add(pathArray[0]);
        }
        return firstNode;
    }
    
    /**
     * 对string的分割
     *
     * @param url
     * @return
     */
    public static String[] strToArray(String url) {
        //转递的url默认以"/"开头
        if (url.startsWith("/")) {
            url = url.substring(1, url.length());
        }
        return url.split("/");
    }
    
    
    /**
     * 以长度来区分数组
     * 得到分类好的数组
     *
     * @param allPathArray
     * @return
     */
    public static Map<String, List<String[]>> getMapByLength(List<String[]> allPathArray) {
        Map<String, List<String[]>> pathMapByLength = new HashMap<>();
        List<String[]> pathArrayByLength1 = new ArrayList<>();
        List<String[]> pathArrayByLength2 = new ArrayList<>();
        List<String[]> pathArrayByLength3 = new ArrayList<>();
        
        for (int i = 0; i < allPathArray.size(); i++) {
            String[] pathArray = allPathArray.get(i);
            if (pathArray.length > 2) {
                pathArrayByLength3.add(pathArray);//保存所有的长度为3的array
                continue;
            }
            if (pathArray.length > 1) {
                pathArrayByLength2.add(pathArray);//保存所有的长度为2的array
                continue;
            } else {
                pathArrayByLength1.add(pathArray);//保存所有的长度为1的array
            }
        }
        pathMapByLength.put("first", pathArrayByLength1);
        pathMapByLength.put("second", pathArrayByLength2);
        pathMapByLength.put("third", pathArrayByLength3);
        
        return pathMapByLength;
    }
    
    
    /**
     * 头结点
     *
     * @param firstNode
     * @return
     */
    public static MenuBo buildRootNode(TreeSet<String> firstNode, Map<String, List<String[]>> pathMapByLength, Set<Authority> menuSet) {
        TreeSet<MenuBo> menuBoSet = new TreeSet<>();
        for (String path : firstNode) {
            TreeSet<MenuBo> tempSet = new TreeSet<>();
            tempSet = checkSecondChildNode(path, pathMapByLength, tempSet, menuSet);
            menuBoSet.add(buildMenu(path, tempSet, menuSet));
        }
        return buildMenu("root", menuBoSet, null);
    }
    
    /**
     * 根据头节点找到第二个节点
     * 并给第二个节点赋值
     *
     * @param path
     * @param pathMapByLength
     * @param menuBoSet
     * @return
     */
    public static TreeSet<MenuBo> checkSecondChildNode(String path, Map<String, List<String[]>> pathMapByLength, TreeSet<MenuBo> menuBoSet, Set<Authority> menuSet) {
        List<String[]> tempList = pathMapByLength.get("second");
        List<String[]> secondList = new ArrayList<>();
        TreeSet<String> tempSet1 = new TreeSet<>();
        TreeSet<String> tempSet2 = new TreeSet<>();
        //长度为2
        for (int i = 0; i < tempList.size(); i++) {
            if (path.equals(tempList.get(i)[0])) {
                tempSet1.add(tempList.get(i)[1]);
            }
        }
        tempList = pathMapByLength.get("third");
        //长度为3
        for (int i = 0; i < tempList.size(); i++) {
            if (path.equals(tempList.get(i)[0])) {
                tempSet2.add(tempList.get(i)[1]);
                secondList.add(tempList.get(i));
            }
        }
        TreeSet<String> tempSet = new TreeSet<>();
        tempSet.addAll(tempSet1);
        tempSet.retainAll(tempSet2);//取出两者交集
        if (tempSet1 != null && tempSet1.size() > 0) {
            tempSet1.removeAll(tempSet);//取出set1中的重复元素
        }
        for (String node2 : tempSet1) {
            menuBoSet.add(buildMenu(path + "/" + node2, null, menuSet));
        }
        for (String node2 : tempSet2) {
            TreeSet<MenuBo> menuBoSet1 = new TreeSet<>();
            menuBoSet.add(buildMenu(path + "/" + node2, checkThirdChildNode(path + "/" + node2, secondList, menuBoSet1, menuSet), menuSet));
        }
        
        return menuBoSet;
    }
    
    /**
     * 根据第二个节点找到第三个节点
     *
     * @param path
     * @param pathList
     * @param menuBoSet
     * @return
     */
    public static TreeSet<MenuBo> checkThirdChildNode(String path, List<String[]> pathList, TreeSet<MenuBo> menuBoSet, Set<Authority> menuSet) {
        //长度为3
        for (int i = 0; i < pathList.size(); i++) {
            if (path.equals(pathList.get(i)[0] + "/" + pathList.get(i)[1])) {
                //最终节点没有子节点
                menuBoSet.add(buildMenu(path + "/" + pathList.get(i)[2], null, menuSet));
            }
        }
        return menuBoSet;
    }
    
    /**
     * 给菜单赋值
     *
     * @param path
     * @return
     */
    public static MenuBo buildMenu(String path, TreeSet<MenuBo> menuBoSet, Set<Authority> menuSet) {
        MenuBo menuBo = new MenuBo();
        menuBo.setPath(path);
        if (menuBoSet != null) {
            menuBo.setChildren(menuBoSet);
        }
        if (menuSet != null && menuSet.size() > 0) {
            for (Authority authority : menuSet) {
                if (authority.getPath().equals("/" + path)) {//通过url的匹配来找到对应的Authority对象
                    menuBo.setRedirect(authority.getRedirect());//开始赋值
                    menuBo.setComponent((authority.getComponent() == null) ? "Layout" : authority.getComponent());
                    Map<String, String> meta = new HashMap<>();
                    meta.put("title", authority.getTitle());
                    menuBo.setMeta(meta);
                }
            }
        }
        return menuBo;
    }
}

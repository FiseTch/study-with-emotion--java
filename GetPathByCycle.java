/**
     * @param url    前台转递的url 格式为：/a/b/c
     * @param menuBo
     * @return
     */
    public static MenuBo buildTree(String url, MenuBo menuBo) {
        //转递的url默认以"/"开头
        if (!StringUtils.isEmpty(url)) {
            if (url.startsWith("/")) {
                url = url.substring(1, url.length());
            }
            String[] splitStr = url.split("/");//第一个路径为空
            menuBo = buildTree(splitStr, menuBo, splitStr.length);
        }
        return menuBo;
    }
    
    /**
     * 使用递归方法对传的url数组进行封装
     *
     * @param path   传的路径 String[]结构为：如果路径为：/a/b/c；则string数组：[a,b,c]
     * @param menuBo menuBo
     * @param index  索引 -数组的长度传当前path的长度。
     * @return
     */
    public static MenuBo buildTree(String[] path, MenuBo menuBo, int index) {
        if (index == 1) {
            menuBo.setPath(path[path.length - 1]);//最终子节点menuBo为空
            menuBo.setComponent("");
            return menuBo;
        } else {
            menuBo.setPath(path[path.length - index]);
            menuBo.setComponent("");
            TreeSet<MenuBo> menuBoSet = new TreeSet<>();
            menuBoSet.add(buildTree(path, new MenuBo(), index - 1));
            menuBo.setChildren(menuBoSet);
            return menuBo;
        }
    }
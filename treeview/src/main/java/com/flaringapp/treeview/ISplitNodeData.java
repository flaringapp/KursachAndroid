package com.flaringapp.treeview;

import java.util.List;

public interface ISplitNodeData {

    String getData();

    List<? extends ISplitNodeData> childNodes();

}

package com.flaringapp.kursach.data.treeSplitter.models;

import com.flaringapp.kursach.data.treeSplitter.TextTreeSplitter.ISplitNode;

import java.util.ArrayList;
import java.util.List;

public class SplitNode implements ISplitNode {

    private String data;

    private final List<ISplitNode> childNodes;

    private int currentLevel = 0;

    public SplitNode() {
        data = "";
        childNodes = new ArrayList<>();
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public List<ISplitNode> childNodes() {
        return childNodes;
    }

    private SplitNode getChildAt(int index) {
        return (SplitNode) childNodes.get(index);
    }

    public void appendSymbol(char symbol) {
        data += symbol;

        if (currentLevel > 0) {
            getChildAt(childNodes.size() - 1).appendSymbol(symbol);
        }
    }

    public void appendSeparatorSymbol(char separatorSymbol) {
        data += separatorSymbol;

        if (currentLevel > 0) {
            getChildAt(childNodes.size() - 1).appendSeparatorSymbol(separatorSymbol);
        }
    }

    public void levelDown() {
        if (currentLevel == 0) {
            childNodes.add(new SplitNode());
        } else if (currentLevel > 0) {
            getChildAt(childNodes.size() - 1).levelDown();
        }

        currentLevel++;
    }

    public void levelUp() {
        if (currentLevel > 0) {
            getChildAt(childNodes.size() - 1).levelUp();
        }

        currentLevel--;
    }
}

package com.flaringapp.base.data.treeSplitter.models;

import com.flaringapp.base.data.treeSplitter.TextTreeSplitter.ISplitNode;

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

    @Override
    public void appendSymbol(char symbol) {
        data += symbol;

        if (currentLevel > 0) {
            childNodes.get(childNodes.size() - 1).appendSymbol(symbol);
        }
    }

    @Override
    public void appendSeparatorSymbol(char separatorSymbol) {
        if (currentLevel > 0) {
            data += separatorSymbol;
            childNodes.get(childNodes.size() - 1).appendSeparatorSymbol(separatorSymbol);
        }
    }

    @Override
    public void levelDown() {
        if (currentLevel == 0) {
            childNodes.add(new SplitNode());
        } else if (currentLevel > 0) {
            childNodes.get(childNodes.size() - 1).levelDown();
        }

        currentLevel++;
    }

    @Override
    public void levelUp() {
        if (currentLevel > 0) {
            childNodes.get(childNodes.size() - 1).levelUp();
            currentLevel--;
        }
    }
}

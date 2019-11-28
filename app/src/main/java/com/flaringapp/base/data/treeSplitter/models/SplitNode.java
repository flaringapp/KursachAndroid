package com.flaringapp.base.data.treeSplitter.models;

import com.flaringapp.base.data.treeSplitter.TextTreeSplitter.ISplitNode;

import java.util.LinkedList;

public class SplitNode implements ISplitNode {

    private String data;

    private final LinkedList<ISplitNode> childNodes;

    private int currentLevel = 0;

    public SplitNode() {
        data = "";
        childNodes = new LinkedList<>();
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public LinkedList<ISplitNode> childNodes() {
        return childNodes;
    }

    @Override
    public void appendSymbol(char symbol) {
        data += symbol;

        if (currentLevel > 0) {
            childNodes.getLast().appendSymbol(symbol);
        }
    }

    @Override
    public void appendSeparatorSymbol(char separatorSymbol) {
        if (currentLevel > 0) {
            data += separatorSymbol;
            childNodes.getLast().appendSeparatorSymbol(separatorSymbol);
        }
    }

    @Override
    public void levelDown() {
        if (currentLevel == 0) {
            childNodes.add(new SplitNode());
        } else if (currentLevel > 0) {
            childNodes.getLast().levelDown();
        }

        currentLevel++;
    }

    @Override
    public void levelUp() {
        if (currentLevel > 0) {
            childNodes.getLast().levelUp();
            currentLevel--;
        }
    }
}

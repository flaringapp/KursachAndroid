package com.flaringapp.kursach.data.treeSplitter.impl;

import com.flaringapp.kursach.data.treeSplitter.TextTreeSplitter;
import com.flaringapp.kursach.data.treeSplitter.impl.validator.SplitterValidator;
import com.flaringapp.kursach.data.treeSplitter.models.SplitNode;
import com.flaringapp.kursach.data.treeSplitter.utils.SplitterUtils;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TextTreeSplitterImpl implements TextTreeSplitter {

    private SplitterValidator splitterValidator;

    public TextTreeSplitterImpl(SplitterValidator splitterValidator) {
        this.splitterValidator = splitterValidator;
    }

    @Override
    public Single<ISplitNode> split(
            String text,
            String startSeparator,
            String endSeparator
    ) {
        Single<ISplitNode> request = Single.create(emitter -> {
            splitterValidator.validateParams(text, startSeparator, endSeparator);

            SplitNode rootNode = new SplitNode();

            for (int i = 0; i < text.length(); i++) {
                if (SplitterUtils.isSeparatorAtIndex(text, startSeparator, i)) {
                    rootNode.levelDown();
                    rootNode.appendSeparatorSymbol(text.charAt(i));
                    i += startSeparator.length() - 1;
                } else if (SplitterUtils.isSeparatorAtIndex(text, endSeparator, i)) {
                    rootNode.appendSeparatorSymbol(text.charAt(i));
                    rootNode.levelUp();
                    i += startSeparator.length() - 1;
                } else {
                    rootNode.appendSymbol(text.charAt(i));
                }
            }

            emitter.onSuccess(rootNode);
        });

        return request
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

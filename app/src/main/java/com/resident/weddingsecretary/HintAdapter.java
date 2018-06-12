package com.resident.weddingsecretary;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class HintAdapter<T extends String>   extends ArrayAdapter<T> {
public HintAdapter(Context theContext, List<T> objects, int theLayoutResId) {
    super(theContext, theLayoutResId, objects);
}

@Override
public int getCount() {
    // don't display last item. It is used as hint.
    int count = super.getCount();
    return count > 0 ? count - 1 : count;
}
}
package com.coderhouse.cache;

import com.coderhouse.model.database.document.UserDocument;

public interface CacheClient<T> {
    T save(String key, T data);
    T recover(String key, Class<T> classValue);
}

package io.swagger.dao.dao;

import com.EbucketList.api.models.WalmartResponse;

import java.net.MalformedURLException;

public interface EcommerceDao {

    public WalmartResponse getItem(String productUrl) throws MalformedURLException;
}

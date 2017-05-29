package org.forumj.common.db.entity;

import org.forumj.common.web.HttpMethod;

import java.util.List;

/**
 * Created by Andrew on 12/05/2017.
 */
public interface Request extends Entity{

    Integer getBotId();

    void setBotId(Integer botId);

    UserAgentHeader getUas();

    void setUas(UserAgentHeader uas);

    HttpMethod getMethod();

    void setMethod(HttpMethod method);

    Long getUserId();

    void setUserId(Long userId);

    IpAddress getIp();

    void setIp(IpAddress ip);

    String getUrl();

    void setUrl(String url);

    Long getTime();

    void setTime(Long time);

    List<HttpHeader> getHeaders();

    void setHeaders(List<HttpHeader> headers);

    List<HttpCookie> getCookies();

    void setCookies(List<HttpCookie> cookies);

    void addCookie(HttpCookie httpCookie);

    void addHeader(HttpHeader httpHeader);
}
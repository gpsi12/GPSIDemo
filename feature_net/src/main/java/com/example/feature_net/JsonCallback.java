package com.example.feature_net;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class JsonCallback<T> extends AbsCallback<T> {


    public JsonCallback() {
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertResponse(Response response) throws Throwable {

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalStateException("没有填写泛型参数");
        }
        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];

        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (rawType != BaseResponse.class) {
            T data = gson.fromJson(jsonReader, type);
            response.close();
            return data;
        } else {
            if (typeArgument == Void.class) {
                BaseResponse baseResponse = gson.fromJson(jsonReader, BaseResponse.class);
                response.close();

                return (T) baseResponse;
            } else if (rawType == BaseResponse.class) {
                BaseResponse BaseResponse = gson.fromJson(jsonReader, type);
                response.close();

                BaseResponse.isSuccess = (BaseResponse.errorCode == 0);

                return (T) BaseResponse;
            } else {
                response.close();
                throw new IllegalStateException("基类错误无法解析!");
            }

        }
    }


    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        Throwable exception = response.getException();

        BaseResponse<T> tBaseResponse = new BaseResponse<>();

        if (exception != null) {
            exception.printStackTrace();
            tBaseResponse.errorMsg = exception.toString();
        }
        response.setBody((T) tBaseResponse);
    }
}
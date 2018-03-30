package ren.vic.data.repository;

import android.content.Context;
import android.util.Base64;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.inject.Inject;

import io.reactivex.Observable;
import ren.vic.data.web.RetrofitInstance;
import ren.vic.data.web.WeatherApi;
import ren.vic.domain.entity.Weather;
import ren.vic.domain.repository.WeatherRepository;

public class WeatherDataRepository implements WeatherRepository {

    private final Context context;
    private final WeatherApi weatherApi;

    @Inject
    WeatherDataRepository(Context context, RetrofitInstance retrofitInstance) {
        this.context = context;
        this.weatherApi = retrofitInstance.getWeatherApi();
    }

    @Override
    public Observable<Weather> weatherByCityName(String cityName) {
        Map<String, String> map = new HashMap<>();
        map.put("username", "HE1712160911301054");
        map.put("location", cityName);
        map.put("t", "1515815034");
        try {
            String sign = getSignature(map, "18759f9478b149fc9f5898fc4569bba0");
            map.put("sign", sign);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherApi.getCurrentWeather(map)
                .flatMap(weatherEntity -> Observable.create(e -> {
                    Weather weather = new Weather();
                    weather.condTxt = weatherEntity.getWeatherNow().condTxt;
                    e.onNext(weather);
                    e.onComplete();
                }));
    }

    private Observable<String> signature(Map<String, String> params, String secret) {
        return Observable.create(e -> {
            String sign = getSignature(params, secret);
            e.onNext(sign);
            e.onComplete();
        });
    }

    /**
     * 和风天气签名生成算法-JAVA版本
     *
     * @param params 请求参数集，所有参数必须已转换为字符串类型
     * @param secret 签名密钥（用户的认证key）
     * @return 签名
     * @throws IOException
     */
    private String getSignature(Map<String, String> params, String secret) throws IOException {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起

        StringBuilder baseString = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            //sign参数 和 空值参数 不加入算法
            if (param.getValue() != null && !"".equals(param.getKey().trim()) && !"sign".equals(param.getKey().trim()) && !"key".equals(param.getKey().trim()) && !"".equals(param.getValue().trim())) {
                baseString.append(param.getKey().trim()).append("=").append(param.getValue().trim()).append("&");
            }
        }
        if (baseString.length() > 0) {
            baseString.deleteCharAt(baseString.length() - 1).append(secret);
        }

        // 使用MD5对待签名串求签
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(baseString.toString().getBytes("UTF-8"));
            return Base64.encodeToString(bytes, Base64.NO_WRAP);
        } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
        }
    }
}

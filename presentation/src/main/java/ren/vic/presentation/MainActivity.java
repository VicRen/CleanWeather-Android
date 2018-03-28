package ren.vic.presentation;

import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import butterknife.BindView;
import ren.vic.presentation.common.BaseActionBarActivity;

public class MainActivity extends BaseActionBarActivity {

    @BindView(R2.id.tvTesting)
    TextView mTvTesting;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mTvTesting.setText("哈哈哈");
        String secTime = String.valueOf(System.currentTimeMillis() / 1000);
        Map<String, String> map = new HashMap<>();
        map.put("username", "HE1712160911301054");
        map.put("t", secTime);
        map.put("location", "ningbo");
        String params = null;
        try {
            String test = getSignature(map, "18759f9478b149fc9f5898fc4569bba0");
            Log.d("VicTesting", "sign=" + test + "&username=HE1712160911301054&location=ningbo&" +
                    "t=" + secTime);
            params = "sign=" + test + "&username=HE1712160911301054&location=ningbo&" +
                    "t=" + secTime;
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String finalParams = params;
        new Thread(new Runnable() {
            @Override
            public void run() {

                StringBuilder sb = new StringBuilder();
                InputStream is = null;
                BufferedReader br = null;
                PrintWriter out = null;
                try {
                    //接口地址
                    String url = "https://free-api.heweather.com/s6/weather";
                    URL uri = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(10000);
                    connection.setRequestProperty("accept", "*/*");
                    //发送参数
                    connection.setDoOutput(true);
                    out = new PrintWriter(connection.getOutputStream());
                    out.print(finalParams);
                    out.flush();
                    //接收结果
                    is = connection.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    //缓冲逐行读取
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    System.out.println("VicTesting" + sb.toString());
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                } finally {
                    //关闭流
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (br != null) {
                            br.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e2) {
                    }
                }
            }
        }).start();
    }

    /**
     * 和风天气签名生成算法-JAVA版本
     *
     * @param params 请求参数集，所有参数必须已转换为字符串类型
     * @param secret 签名密钥（用户的认证key）
     * @return 签名
     * @throws IOException
     */
    public static String getSignature(Map<String, String> params, String secret) throws IOException {
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

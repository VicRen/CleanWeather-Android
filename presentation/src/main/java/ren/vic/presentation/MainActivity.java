package ren.vic.presentation;

import android.util.Base64;
import android.widget.TextView;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.inject.Inject;

import butterknife.BindView;
import ren.vic.presentation.common.BaseActionBarActivity;

public class MainActivity extends BaseActionBarActivity implements MainContract.View {

    @BindView(R2.id.tvTesting)
    TextView mTvTesting;

    @Inject
    MainPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mTvTesting.setText("哈哈哈");
        mPresenter.setView(this);
    }

    @Override
    public void onShowText(String text) {
        mTvTesting.setText(text);
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

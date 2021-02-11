package other;

import java.util.Map;
import java.util.TreeMap;

public class MerchantIndustryTypeHolder {

    private Map<Integer, String> industryType;

    public MerchantIndustryTypeHolder() {
        industryType = new TreeMap<>();
        init();
        printn();
    }

    private void init() {
        final String[][] rawList = {
                {"4215", "快递服务（空运、地面运输或海运）"}, {"4511", "航空公司"},
                {"4814", "电信服务，包括本地和长途电话、信用卡电话、磁卡电话和传真"}, {"4816", "计算机网络/信息服务"},
                {"4900", "公共事业（电力、煤气、自来水、清洁服务）"}, {"5065", "电器零件和设备（批发商）"},
                {"5111", "文具、办公用品、复印纸和书写纸（批发商）"}, {"5251", "五金商店"},
                {"5311", "百货商店"}, {"5398", "大型企业批发"},
                {"5399", "其他综合零售(各类日用品)"}, {"5499", "各类食品店及专门食品零售店"},
                {"5641", "婴儿、儿童服装店"}, {"5699", "各类服装及饰物店"},
                {"5714", "帏帐、窗帘、室内装潢商店"}, {"5734", "计算机软件商店"},
                {"5912", "药房、药店"}, {"5941", "体育用品店"},
                {"5942", "书店"}, {"5992", "花店"},
                {"5998", "其他批发商"}, {"6012", "金融机构－商品和服务"},
                {"6300", "保险销售、保险业和保险金"}, {"7230", "美容理发店"},
                {"7299", "未列入其他代码的其他个人服务"}, {"7375", "信息检索服务"},
                {"7379", "未列入其他代码的计算机维护和修理服务"}, {"7399", "未列入其他代码的商业服务"},
                {"7922", "戏剧制片（不含电影）、演出和票务"}, {"7993", "电子游戏供给"},
                {"7995", "彩票销售"}, {"8062", "公立医院"},
                {"8220", "普通高校（公立）"}, {"8299", "其他学校和教育服务"},
                {"8398", "慈善和社会公益服务组织"}, {"8651", "政治组织（政府机构）"}
        };

        for (String[] pair : rawList) {
            industryType.put(Integer.valueOf(pair[0]), pair[1]);
        }
    }

    private void printn() {
        System.out.printf("Size: %d %n", industryType.size());
        for (Map.Entry<Integer, String> entry : industryType.entrySet()) {
            System.out.printf("Key: %d, Value: %s %n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        new MerchantIndustryTypeHolder();
    }

}

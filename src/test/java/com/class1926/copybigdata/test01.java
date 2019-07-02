package com.class1926.copybigdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.class1926.copybigdata.entity.MapResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author junhi
 * @date 2019/6/19 13:41
 */
public class test01 {


    @Test
    public void test01() {
        List<MapResult> mapResult = new ArrayList<>();
        String b = "[{\"name\":\"三亚\",\"value\":2.0},{\"name\":\"三明\",\"value\":0.0},{\"name\":\"三门峡\",\"value\":0.17391304347826086},{\"name\":\"上海\",\"value\":2.5284114475321444},{\"name\":\"上饶\",\"value\":1.7},{\"name\":\"东莞\",\"value\":1.9557661927330174},{\"name\":\"东营\",\"value\":0.5714285714285714},{\"name\":\"中卫\",\"value\":1.0},{\"name\":\"中山\",\"value\":1.6906474820143884},{\"name\":\"临汾\",\"value\":4.0},{\"name\":\"临沂\",\"value\":1.2142857142857142},{\"name\":\"临沧\",\"value\":2.0},{\"name\":\"丹东\",\"value\":2.4285714285714284},{\"name\":\"丹阳\",\"value\":1.3},{\"name\":\"丽水\",\"value\":0.0},{\"name\":\"义乌\",\"value\":1.6842105263157894},{\"name\":\"乌鲁木齐\",\"value\":1.6470588235294117},{\"name\":\"乐山\",\"value\":1.2857142857142858},{\"name\":\"九江\",\"value\":1.5714285714285714},{\"name\":\"云浮\",\"value\":1.375},{\"name\":\"亳州\",\"value\":0.0},{\"name\":\"仙桃\",\"value\":0.0},{\"name\":\"佛山\",\"value\":2.141384388807069},{\"name\":\"保定\",\"value\":1.5185185185185186},{\"name\":\"信阳\",\"value\":3.0},{\"name\":\"克拉玛依\",\"value\":1.0},{\"name\":\"六安\",\"value\":0.4444444444444444},{\"name\":\"兰州\",\"value\":1.6041666666666667},{\"name\":\"内江\",\"value\":0.5},{\"name\":\"内蒙古\",\"value\":2.0},{\"name\":\"凉山\",\"value\":0.0},{\"name\":\"包头\",\"value\":5.0},{\"name\":\"北京\",\"value\":2.374188176519567},{\"name\":\"北海\",\"value\":0.6666666666666666},{\"name\":\"十堰\",\"value\":1.25},{\"name\":\"南京\",\"value\":2.142559109874826},{\"name\":\"南充\",\"value\":0.75},{\"name\":\"南宁\",\"value\":1.7428571428571429},{\"name\":\"南昌\",\"value\":1.7884615384615385},{\"name\":\"南通\",\"value\":1.5514018691588785},{\"name\":\"南阳\",\"value\":1.0},{\"name\":\"厦门\",\"value\":1.904191616766467},{\"name\":\"双鸭山\",\"value\":2.0},{\"name\":\"台州\",\"value\":2.090909090909091},{\"name\":\"合肥\",\"value\":1.8249763481551562},{\"name\":\"吉安\",\"value\":2.0},{\"name\":\"吕梁\",\"value\":1.0},{\"name\":\"吴忠\",\"value\":2.0},{\"name\":\"周口\",\"value\":0.0},{\"name\":\"呼伦贝尔\",\"value\":0.5},{\"name\":\"呼和浩特\",\"value\":1.5476190476190477},{\"name\":\"咸宁\",\"value\":2.5},{\"name\":\"咸阳\",\"value\":1.9090909090909092},{\"name\":\"哈尔滨\",\"value\":1.7743362831858407},{\"name\":\"唐山\",\"value\":2.088235294117647},{\"name\":\"商丘\",\"value\":0.75},{\"name\":\"嘉兴\",\"value\":1.3790849673202614},{\"name\":\"固原\",\"value\":1.0},{\"name\":\"大理\",\"value\":2.0},{\"name\":\"大连\",\"value\":1.7524271844660195},{\"name\":\"天水\",\"value\":2.25},{\"name\":\"天津\",\"value\":1.7092592592592593},{\"name\":\"天门\",\"value\":0.0},{\"name\":\"太仓\",\"value\":2.3125},{\"name\":\"太原\",\"value\":1.8571428571428572},{\"name\":\"威海\",\"value\":1.75},{\"name\":\"娄底\",\"value\":0.0},{\"name\":\"孝感\",\"value\":1.3333333333333333},{\"name\":\"宁夏\",\"value\":1.25},{\"name\":\"宁德\",\"value\":2.764705882352941},{\"name\":\"宁波\",\"value\":1.73502722323049},{\"name\":\"安庆\",\"value\":1.0},{\"name\":\"安阳\",\"value\":0.0},{\"name\":\"宜宾\",\"value\":0.0},{\"name\":\"宜昌\",\"value\":1.6},{\"name\":\"宜春\",\"value\":1.3333333333333333},{\"name\":\"宝鸡\",\"value\":1.5},{\"name\":\"宣城\",\"value\":1.0},{\"name\":\"宿州\",\"value\":1.0},{\"name\":\"宿迁\",\"value\":1.5384615384615385},{\"name\":\"岳阳\",\"value\":2.142857142857143},{\"name\":\"巴中\",\"value\":0.0},{\"name\":\"巴音郭楞\",\"value\":0.0},{\"name\":\"常州\",\"value\":1.88212927756654},{\"name\":\"常德\",\"value\":1.75},{\"name\":\"常熟\",\"value\":2.0},{\"name\":\"平顶山\",\"value\":0.0},{\"name\":\"广元\",\"value\":0.0},{\"name\":\"广安\",\"value\":0.25},{\"name\":\"广州\",\"value\":2.291214470284238},{\"name\":\"广西\",\"value\":0.42857142857142855},{\"name\":\"廊坊\",\"value\":1.5333333333333334},{\"name\":\"开封\",\"value\":1.0},{\"name\":\"开平\",\"value\":0.0},{\"name\":\"张家口\",\"value\":1.4545454545454546},{\"name\":\"张家港\",\"value\":1.2553191489361701},{\"name\":\"张家界\",\"value\":0.5},{\"name\":\"徐州\",\"value\":1.5578947368421052},{\"name\":\"德州\",\"value\":2.5294117647058822},{\"name\":\"德阳\",\"value\":0.0},{\"name\":\"怀化\",\"value\":3.4193548387096775},{\"name\":\"怒江\",\"value\":2.0},{\"name\":\"恩施\",\"value\":0.0},{\"name\":\"惠州\",\"value\":1.8780487804878048},{\"name\":\"成都\",\"value\":2.262485481997677},{\"name\":\"扬州\",\"value\":1.7692307692307692},{\"name\":\"承德\",\"value\":1.5},{\"name\":\"抚州\",\"value\":0.6666666666666666},{\"name\":\"拉萨\",\"value\":1.8},{\"name\":\"揭阳\",\"value\":0.16666666666666666},{\"name\":\"攀枝花\",\"value\":0.0},{\"name\":\"新乡\",\"value\":1.7777777777777777},{\"name\":\"新余\",\"value\":0.0},{\"name\":\"新疆\",\"value\":1.1666666666666667},{\"name\":\"无锡\",\"value\":2.119448698315467},{\"name\":\"日照\",\"value\":1.7692307692307692},{\"name\":\"昆山\",\"value\":2.1649484536082473},{\"name\":\"昆明\",\"value\":1.8283018867924528},{\"name\":\"昌吉\",\"value\":2.6153846153846154},{\"name\":\"昭通\",\"value\":0.5},{\"name\":\"晋城\",\"value\":3.0},{\"name\":\"普洱\",\"value\":2.0},{\"name\":\"景德镇\",\"value\":0.0},{\"name\":\"曲靖\",\"value\":0.0},{\"name\":\"朝阳\",\"value\":1.8},{\"name\":\"杭州\",\"value\":2.373529411764706},{\"name\":\"枣庄\",\"value\":2.7},{\"name\":\"柳州\",\"value\":1.5961538461538463},{\"name\":\"株洲\",\"value\":1.6666666666666667},{\"name\":\"桂林\",\"value\":1.6333333333333333},{\"name\":\"梅州\",\"value\":0.3333333333333333},{\"name\":\"梧州\",\"value\":0.6},{\"name\":\"楚雄\",\"value\":1.0},{\"name\":\"武汉\",\"value\":2.1012311901504788},{\"name\":\"毕节\",\"value\":0.5},{\"name\":\"永州\",\"value\":0.6666666666666666},{\"name\":\"汕头\",\"value\":1.8709677419354838},{\"name\":\"汕尾\",\"value\":0.3333333333333333},{\"name\":\"江门\",\"value\":1.5},{\"name\":\"池州\",\"value\":0.0},{\"name\":\"沈阳\",\"value\":1.578512396694215},{\"name\":\"沧州\",\"value\":1.7142857142857142},{\"name\":\"河源\",\"value\":1.6666666666666667},{\"name\":\"泉州\",\"value\":1.5571428571428572},{\"name\":\"泰兴\",\"value\":0.0},{\"name\":\"泰安\",\"value\":1.9545454545454546},{\"name\":\"泰州\",\"value\":1.5862068965517242},{\"name\":\"泸州\",\"value\":3.2083333333333335},{\"name\":\"洛阳\",\"value\":1.7857142857142858},{\"name\":\"济南\",\"value\":1.8492753623188405},{\"name\":\"济宁\",\"value\":1.25},{\"name\":\"济源\",\"value\":0.0},{\"name\":\"海口\",\"value\":1.5420560747663552},{\"name\":\"海宁\",\"value\":1.0},{\"name\":\"海西\",\"value\":5.0},{\"name\":\"淄博\",\"value\":1.4545454545454546},{\"name\":\"淮北\",\"value\":0.0},{\"name\":\"淮南\",\"value\":0.0},{\"name\":\"淮安\",\"value\":0.9615384615384616},{\"name\":\"深圳\",\"value\":2.6544590079671035},{\"name\":\"清远\",\"value\":1.2307692307692308},{\"name\":\"温州\",\"value\":1.3563218390804597},{\"name\":\"湖州\",\"value\":2.34},{\"name\":\"湘潭\",\"value\":1.6},{\"name\":\"湘西\",\"value\":0.0},{\"name\":\"湛江\",\"value\":1.4615384615384615},{\"name\":\"滁州\",\"value\":1.6},{\"name\":\"滨州\",\"value\":1.1666666666666667},{\"name\":\"漯河\",\"value\":0.5},{\"name\":\"漳州\",\"value\":1.6666666666666667},{\"name\":\"潍坊\",\"value\":1.2307692307692308},{\"name\":\"潜江\",\"value\":0.0},{\"name\":\"潮州\",\"value\":1.0},{\"name\":\"澄迈\",\"value\":2.0},{\"name\":\"濮阳\",\"value\":0.0},{\"name\":\"烟台\",\"value\":1.328358208955224},{\"name\":\"焦作\",\"value\":1.2},{\"name\":\"玉林\",\"value\":1.3333333333333333},{\"name\":\"玉溪\",\"value\":5.0},{\"name\":\"珠海\",\"value\":2.1414634146341465},{\"name\":\"甘孜\",\"value\":0.0},{\"name\":\"白城\",\"value\":2.7222222222222223},{\"name\":\"白银\",\"value\":1.0},{\"name\":\"益阳\",\"value\":1.625},{\"name\":\"盐城\",\"value\":0.7916666666666666},{\"name\":\"盘锦\",\"value\":3.6666666666666665},{\"name\":\"眉山\",\"value\":0.0},{\"name\":\"石家庄\",\"value\":0.6236162361623616},{\"name\":\"石河子\",\"value\":1.0},{\"name\":\"神农架\",\"value\":0.0},{\"name\":\"福州\",\"value\":1.4050522648083623},{\"name\":\"秦皇岛\",\"value\":2.129032258064516},{\"name\":\"绍兴\",\"value\":1.8611111111111112},{\"name\":\"绵阳\",\"value\":1.1481481481481481},{\"name\":\"聊城\",\"value\":0.6},{\"name\":\"肇庆\",\"value\":1.8125},{\"name\":\"自贡\",\"value\":0.0},{\"name\":\"舟山\",\"value\":0.8},{\"name\":\"芜湖\",\"value\":1.7303370786516854},{\"name\":\"苏州\",\"value\":2.0217881292261457},{\"name\":\"茂名\",\"value\":1.9333333333333333},{\"name\":\"荆州\",\"value\":1.8333333333333333},{\"name\":\"荆门\",\"value\":2.0},{\"name\":\"莆田\",\"value\":1.6428571428571428},{\"name\":\"莱芜\",\"value\":1.5},{\"name\":\"菏泽\",\"value\":0.6666666666666666},{\"name\":\"萍乡\",\"value\":1.0},{\"name\":\"营口\",\"value\":1.0},{\"name\":\"蚌埠\",\"value\":1.5714285714285714},{\"name\":\"衡水\",\"value\":2.2},{\"name\":\"衡阳\",\"value\":1.0},{\"name\":\"衢州\",\"value\":0.5714285714285714},{\"name\":\"襄阳\",\"value\":1.6538461538461537},{\"name\":\"西双版纳\",\"value\":5.0},{\"name\":\"西宁\",\"value\":1.6875},{\"name\":\"西安\",\"value\":2.2142857142857144},{\"name\":\"西昌\",\"value\":0.0},{\"name\":\"许昌\",\"value\":1.1666666666666667},{\"name\":\"贵港\",\"value\":2.0},{\"name\":\"贵阳\",\"value\":1.9735099337748345},{\"name\":\"贺州\",\"value\":0.6666666666666666},{\"name\":\"资阳\",\"value\":0.0},{\"name\":\"赣州\",\"value\":2.5142857142857142},{\"name\":\"赤峰\",\"value\":1.0},{\"name\":\"辽阳\",\"value\":2.0},{\"name\":\"达州\",\"value\":1.3333333333333333},{\"name\":\"运城\",\"value\":2.5},{\"name\":\"连云港\",\"value\":1.206896551724138},{\"name\":\"迪庆\",\"value\":3.0},{\"name\":\"遂宁\",\"value\":0.0},{\"name\":\"遵义\",\"value\":3.3333333333333335},{\"name\":\"邓州\",\"value\":0.0},{\"name\":\"邢台\",\"value\":1.7142857142857142},{\"name\":\"邯郸\",\"value\":1.2727272727272727},{\"name\":\"邵阳\",\"value\":0.6666666666666666},{\"name\":\"郑州\",\"value\":1.775609756097561},{\"name\":\"郴州\",\"value\":2.5789473684210527},{\"name\":\"鄂尔多斯\",\"value\":1.5},{\"name\":\"鄂州\",\"value\":1.75},{\"name\":\"重庆\",\"value\":2.4065372829417773},{\"name\":\"金华\",\"value\":1.7894736842105263},{\"name\":\"钦州\",\"value\":3.0},{\"name\":\"铜陵\",\"value\":1.3333333333333333},{\"name\":\"银川\",\"value\":1.6582278481012658},{\"name\":\"锦州\",\"value\":1.5},{\"name\":\"镇江\",\"value\":1.6},{\"name\":\"长春\",\"value\":1.892156862745098},{\"name\":\"长沙\",\"value\":2.381011097410604},{\"name\":\"长治\",\"value\":3.0},{\"name\":\"阜阳\",\"value\":1.2},{\"name\":\"阳江\",\"value\":0.0},{\"name\":\"阳泉\",\"value\":1.0},{\"name\":\"阿克苏\",\"value\":3.0},{\"name\":\"阿坝\",\"value\":1.0},{\"name\":\"随州\",\"value\":0.0},{\"name\":\"雄安新区\",\"value\":3.0},{\"name\":\"雅安\",\"value\":0.0},{\"name\":\"青岛\",\"value\":1.0987012987012987},{\"name\":\"靖江\",\"value\":1.0625},{\"name\":\"鞍山\",\"value\":1.8125},{\"name\":\"韶关\",\"value\":0.875},{\"name\":\"马鞍山\",\"value\":0.9090909090909091},{\"name\":\"驻马店\",\"value\":1.5},{\"name\":\"鹤壁\",\"value\":2.0},{\"name\":\"鹰潭\",\"value\":1.6666666666666667},{\"name\":\"黄冈\",\"value\":3.3333333333333335},{\"name\":\"黄山\",\"value\":0.0},{\"name\":\"黄石\",\"value\":2.2},{\"name\":\"黔南\",\"value\":0.0},{\"name\":\"齐齐哈尔\",\"value\":2.0},{\"name\":\"龙岩\",\"value\":1.0}]";
        System.out.println(b);

        for (int i = 0; i < 10; i++) {
            MapResult build = MapResult.builder().name("nijie" + i).value(i).build();
            mapResult.add(build);
        }

        System.out.println(mapResult);

        ObjectMapper mapper = new ObjectMapper();
        String s = null;
        try {
            s = mapper.writeValueAsString(mapResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            JavaType javaType = TypeFactory.defaultInstance().constructParametricType(List.class, MapResult.class);
            List<MapResult> list = mapper.readValue(s, javaType);
            System.out.println(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 探究二维数组与json之间的互换
     *
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {

        Object[][] obj = {{"三亚", "三明", "三门峡", "上海"},
                {21, 22, 23, 24},
                {31, 32, 33, 34},
                {41, 42, 43, 44}
        };
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(obj);
        System.out.println(str);


        JsonNode jsonNode = mapper.readTree(str);
        Object[][] objects = new Object[4][4];

        for (int i = 0; i < jsonNode.size(); i++) {
            JsonNode jsonNode1 = jsonNode.get(i);
            JavaType javaType = TypeFactory.defaultInstance().constructParametricType(List.class, Object.class);
            List<Object> list = mapper.readValue(jsonNode1.toString(), javaType);

            for (int j = 0; j < list.size(); j++) {
                objects[i][j] = list.get(j);
            }
        }

        System.out.println(objects.length);

        for (int i = 0; i < objects.length; i++) {
            System.out.println(Arrays.toString(objects[i]));

        }

        List<Object> list = new ArrayList<>();
        List<Object> list1 = Arrays.asList("三亚", "三明", "三门峡", "上海");
        List<Object> list2 = Arrays.asList(21, 22, 23, 24);
        List<Object> list3 = Arrays.asList(31, 32, 33, 34);
        List<Object> list4 = Arrays.asList(41, 42, 43, 44);
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

        System.out.println(list);

    }

    /**
     * 获得路径，中文转码
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test03() throws UnsupportedEncodingException {
        String str = test01.class.getClassLoader().getResource("redis_server.bat").getPath();
        String decode = URLDecoder.decode(str, "utf-8");
        System.out.println(decode);

        System.out.println(test01.class.getClassLoader().getResource("redis_server.bat").getPath());
    }

    /**
     * 探究二维数组与json之间的互换
     *
     * @throws IOException
     */
    @Test
    public void test04() throws IOException {

        Object[][] obj = {{"三亚", "三明", "三门峡", "上海"},
                {21, 22, 23, 24},
                {31, 32, 33, 34},
                {41, 42, 43, 44}
        };
        String str = JSON.toJSONString(obj);
        System.out.println(str);

        List<Object> list = JSON.parseArray(str, Object.class);
        String objects2 =  list.get(0).toString();
        List<Object> list2 = JSON.parseArray(objects2, Object.class);
        System.out.println(list2.get(0));




//        JsonNode jsonNode = mapper.readTree(str);
//        Object[][] objects = new Object[4][4];
//
//        for (int i = 0; i < jsonNode.size(); i++) {
//            JsonNode jsonNode1 = jsonNode.get(i);
//            JavaType javaType = TypeFactory.defaultInstance().constructParametricType(List.class, Object.class);
//            List<Object> list = mapper.readValue(jsonNode1.toString(), javaType);
//
//            for (int j = 0; j < list.size(); j++) {
//                objects[i][j] = list.get(j);
//            }
//        }
//
//        System.out.println(objects.length);
//
//        for (int i = 0; i < objects.length; i++) {
//            System.out.println(Arrays.toString(objects[i]));
//
//        }
//
//        List<Object> list = new ArrayList<>();
//        List<Object> list1 = Arrays.asList("三亚", "三明", "三门峡", "上海");
//        List<Object> list2 = Arrays.asList(21, 22, 23, 24);
//        List<Object> list3 = Arrays.asList(31, 32, 33, 34);
//        List<Object> list4 = Arrays.asList(41, 42, 43, 44);
//        list.add(list1);
//        list.add(list2);
//        list.add(list3);
//        list.add(list4);
//
//        System.out.println(list);

    }


}

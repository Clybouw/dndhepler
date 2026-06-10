package com.example.dndhepler;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileOutputStream;
import java.util.stream.Collectors;

public class Loading {
    //Обновление прогресса
    public interface LoadListener {
        void onProgress(int current,
                        int total,
                        String name);
        void onFinish();
        void onError (Exception e);
    }

    private static String getParam(Document doc, String paramName) {
        Elements params = doc.select(".card__article-body li");
        for (Element li : params) {
            String strong = li.select("strong").text();
            if (strong.contains(paramName)) {
                return li.ownText().trim();
            }
        }
        return "";
    }

    private static String safeText(Element el) {
        return el != null ? el.text().trim() : "";
    }

    //Методы загрузки
    public static void bestiary24Load(Context context, LoadListener listener) {
        new Thread(() -> {
            try {
                // Загрузка страницы bestiary
                Document bestiary = Jsoup.connect("https://next.dnd.su/bestiary/")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                        .referrer("https://www.google.com")
                        .timeout(10000)
                        .get();
                // Получение карточек монстров
                Elements bestiaryUnits = bestiary.select("script");
                String listJson = null;
                for (Element script : bestiaryUnits) {
                    String html = script.html();

                    if (html.contains("window.LIST")) {
                        listJson = html;
                        break;
                    }
                }
                listJson = listJson.replace("window.LIST = ", "");
                listJson = listJson.substring(0, listJson.indexOf("};") + 1);
                JSONObject listObject = new JSONObject(listJson);
                JSONArray cards = listObject.getJSONArray("cards");
                // JSON массив
                JSONArray bestiaryArray = new JSONArray();
                // Количество монстров
                int total = cards.length();
                // Проходимся по монстрам
                for (int i = 0; i < cards.length(); i++) {
                    JSONObject card = cards.getJSONObject(i);
                    // Имя монстра
                    String name = card.getString("title");
                    // Опасность монстра
                    String danger = card.getString("challenge");
                    //Обновление прогресса
                    // Новый json объект
                    JSONObject jsonObject = new JSONObject();
                    // Получение href
                    String href = card.getString("link");
                    // Формирование ссылки
                    String url = href.startsWith("http")
                            ? href
                            : "https://next.dnd.su" + href;
                    listener.onProgress(i + 1, total, name);
                    // Загрузка страницы монстра
                    Document UnitInfo = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)").referrer("https://www.google.com").timeout(10000).get();
                    // Получение характеристик
                    String type = UnitInfo.select("li.size-type-alignment").text().replace("?", "");
                    String ac = UnitInfo.select(".subsection-ac").text();
                    String initiative = UnitInfo.select(".subsection-initiative").text();
                    Element hpElement = UnitInfo.selectFirst("li:contains(Хиты)");
                    String hp = hpElement.text();
                    String move = UnitInfo.select("li:contains(Скорость)").text();
                    // Сборка характеристик
                    String abilities = UnitInfo.select(".stat-pair-row")
                            .stream()
                            .map(Element::text)
                            .collect(Collectors.joining("\n"));
                    // Остальные данные
                    String skills = UnitInfo.select(".skills").text();
                    String resists = UnitInfo.select("li:contains(Сопротивление урону)").text();
                    String immuns = UnitInfo.select("li:contains(Иммунитеты)").text().replace("?", "");;
                    String feel = UnitInfo.select("li:contains(Чувства)").text().replace("?", "");
                    String languages = UnitInfo.select("li:contains(Языки)").text();
                    String area = UnitInfo.select("li:contains(Среда обитания)").text();
                    String exp = UnitInfo.select("li:contains(Опасность)").text();
                    //Сборка особенностей
                    String features = "";
                    Elements traits = UnitInfo.select("li.article-body__monster__section ");
                    for (Element trait : traits) {
                        Element title = trait.selectFirst("h3.article-body__monster__section-title");
                        if (title != null && title.text().trim().equalsIgnoreCase("Особенности")) {
                            features = trait.select("p[class^=article-body__]")
                                    .stream()
                                    .map(Element::text)
                                    .reduce((a, b) -> a + "\n\n" + b)
                                    .orElse("");
                            break;
                        }
                    }
                    // Сборка атак
                    String actions = "";
                    Elements attacks = UnitInfo.select("li.article-body__monster__section ");
                    for (Element attack : attacks) {
                        Element title = attack.selectFirst("h3.article-body__monster__section-title");
                        if (title != null && title.text().trim().equalsIgnoreCase("Действия")) {
                            actions = attack.select("p[class^=article-body__]")
                                    .stream()
                                    .map(Element::text)
                                    .reduce((a, b) -> a + "\n\n" + b)
                                    .orElse("");
                            break;
                        }
                    }
                    //Сборка легендарных действий
                    String legendaryActions = "";
                    Elements lActions = UnitInfo.select("li.article-body__monster__section ");
                    for (Element lAction : lActions) {
                        Element title = lAction.selectFirst("h3.article-body__monster__section-title");
                        if (title != null && title.text().trim().equalsIgnoreCase("Легендарные действия")) {
                            legendaryActions = lAction.select("p[class^=article-body__]")
                                    .stream()
                                    .map(Element::text)
                                    .reduce((a, b) -> a + "\n\n" + b)
                                    .orElse("");
                            break;
                        }
                    }
                    //Бонусные действия
                    String bonusActions = "";
                    Elements bACtions = UnitInfo.select("li.article-body__monster__section ");
                    for (Element bAction : bACtions) {
                        Element title = bAction.selectFirst("h3.article-body__monster__section-title");
                        if (title != null && title.text().trim().equalsIgnoreCase("Бонусные действия")) {
                            bonusActions = bAction.select("p[class^=article-body__]")
                                    .stream()
                                    .map(Element::text)
                                    .reduce((a, b) -> a + "\n\n" + b)
                                    .orElse("");
                            break;
                        }
                    }
                    //Реакции
                    String reactions = "";
                    Elements reacts = UnitInfo.select("li.article-body__monster__section ");
                    for (Element react : reacts) {
                        Element title = react.selectFirst("h3.article-body__monster__section-title");
                        if (title != null && title.text().trim().equalsIgnoreCase("Реакции")) {
                            reactions = react.select("p[class^=article-body__]")
                                    .stream()
                                    .map(Element::text)
                                    .reduce((a, b) -> a + "\n\n" + b)
                                    .orElse("");
                            break;
                        }
                    }
                    // Заполнение json объекта
                    jsonObject.put("name", name);
                    jsonObject.put("danger", danger);
                    jsonObject.put("type", type);
                    jsonObject.put("ac", ac);
                    jsonObject.put("initiative", initiative);
                    jsonObject.put("hp", hp);
                    jsonObject.put("move", move);
                    jsonObject.put("abilities", abilities);
                    jsonObject.put("skills", skills);
                    jsonObject.put("resists", resists);
                    jsonObject.put("immuns", immuns);
                    jsonObject.put("feel", feel);
                    jsonObject.put("languages", languages);
                    jsonObject.put("area", area);
                    jsonObject.put("exp", exp);
                    jsonObject.put("features", features);
                    jsonObject.put("actions", actions);
                    jsonObject.put("legendaryActions", legendaryActions);
                    jsonObject.put("bonusActions", bonusActions);
                    jsonObject.put("reactions", reactions);
                    // Добавление монстра в json массив
                    bestiaryArray.put(jsonObject);
                    // Задержка между запросами
                    Thread.sleep(500);
                }
                // Сохранение json файла
                FileOutputStream fos = context.openFileOutput("Bestiary5e24.json", Context.MODE_PRIVATE);
                fos.write(bestiaryArray.toString(4).getBytes());
                fos.close();
                //Завершение
                listener.onFinish();

            } catch (Exception e) {
                listener.onError(e);
            }
        }).start();
    }

    public static void bestiary14Load(Context context, LoadListener listener) {
        new Thread(() -> {
            try {
                Document bestiary = Jsoup.connect("https://5e14.dnd.su/piece/bestiary/index-list/?content=multiverse").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)").referrer("https://www.google.com").timeout(10000).get();
                Elements cards = bestiary.select(".list-item__beast");
                JSONArray bestiaryArray = new JSONArray();
                int total = cards.size();
                //Ищем в бестиарии
                for (int i = 0; i < cards.size(); i++) {
                    Element unit = cards.get(i);
                    String name = unit.select(".list-item-title").text();
                    String danger = unit.select(".list-mark__danger").text();
                    String href = unit.select("a").attr("href");
                    String url = href.startsWith("http")
                            ? href
                            : "https://5e14.dnd.su" + href;
                    listener.onProgress(i + 1, total, name);
                    Document unitInfo = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)").referrer("https://www.google.com").timeout(10000).get();;
                    String type = safeText(unitInfo.selectFirst("li.size-type-alignment"))
                            .replace("?", "");
                    String ac = safeText(unitInfo.selectFirst("li:contains(Класс доспеха)"));
                    Element initiativeElement = unitInfo.selectFirst(".stat[title=Ловкость] strong");
                    String initiative = safeText(initiativeElement);
                    Element hpElement = unitInfo.selectFirst("li:contains(Хиты)");
                    String hp = safeText(hpElement);
                    String move = safeText(unitInfo.selectFirst("li:contains(Скорость)"));
                    StringBuilder abilitiesBuilder = new StringBuilder();
                    Element abilitiesBlock = unitInfo.selectFirst("li.abilities");
                    if (abilitiesBlock != null) {
                        Elements stats = abilitiesBlock.select(".stat");
                        for (Element stat : stats) {
                            abilitiesBuilder.append(stat.text()).append("\n");
                        }
                    }
                    String saves = "";
                    for (Element item : unitInfo.select("li")) {
                        if (item.hasClass("skills")) break;
                        if (item.text().contains("Спасброски")) {
                            saves = item.text().trim();
                            break;
                        }
                    }
                    if (!saves.isEmpty()) {
                        abilitiesBuilder.append("\n\n").append(saves);
                    }
                    Element bonus = unitInfo.selectFirst("li:contains(Бонус мастерства)");
                    if (bonus != null) {
                        abilitiesBuilder.append("\n").append(bonus.text());
                    }
                    String abilities = abilitiesBuilder.toString().trim();
                    String skills = safeText(unitInfo.selectFirst(".skills"));
                    String resists = safeText(unitInfo.selectFirst("li:contains(Сопротивление урону)"));
                    StringBuilder immunsBuilder = new StringBuilder();
                    Elements immunsList = unitInfo.select("li:contains(Иммунитет к)");
                    for (Element immun : immunsList) {
                        String text = immun.text().replace("?", "").trim();
                        if (!text.isEmpty()) {
                            if (immunsBuilder.length() > 0) {
                                immunsBuilder.append(text);
                            }
                        }
                    }
                    String immuns = immunsBuilder.toString();
                    String feel = safeText(unitInfo.selectFirst("li:contains(Чувства)")).replace("?", "");
                    String languages = safeText(unitInfo.selectFirst("li:contains(Языки)"));
                    String area = safeText(unitInfo.selectFirst("li:contains(Местность обитания)"));
                    String exp = safeText(unitInfo.selectFirst("li:contains(Опасность)"));
                    //сборка особенностей
                    StringBuilder featuresBuilder = new StringBuilder();
                    Elements traits = unitInfo.select("li.subsection.desc");
                    for (Element trait : traits) {
                        if (trait.selectFirst("div.additionalInfo") != null) {
                            continue;
                        }
                        Element title = trait.selectFirst("h3.subsection-title");
                        if (title == null) {
                            String text = trait.select("p")
                                    .stream()
                                    .map(p -> p.text().trim())
                                    .filter(s -> !s.isEmpty())
                                    .collect(Collectors.joining("\n\n"));
                            if (!text.isEmpty()) {
                                if (featuresBuilder.length() > 0) {
                                    featuresBuilder.append("\n\n");
                                }
                                featuresBuilder.append(text);
                            }
                        }
                    }
                    String features = featuresBuilder.toString();
                    // Сборка атак
                    String actions = "";
                    Elements attacks = unitInfo.select("li.subsection.desc");
                    for (Element attack : attacks) {
                        Element title = attack.selectFirst("h3.subsection-title");
                        if (title != null && title.text().trim().equalsIgnoreCase("Действия")) {
                            actions = attack.select("p")
                                    .stream()
                                    .map(p -> p.text().trim())
                                    .filter(s -> !s.isEmpty())
                                    .collect(Collectors.joining("\n\n"));
                            break;
                        }
                    }
                    //Сборка легендарных действий
                    String legendaryActions = "";
                    Elements lActions = unitInfo.select("li.subsection.desc");
                    for (Element lAction : lActions) {
                        Element title = lAction.selectFirst("h3.subsection-title");
                        if (title != null && title.text().trim().equalsIgnoreCase("Легендарные действия")) {
                            legendaryActions = lAction.select("p")
                                    .stream()
                                    .map(p -> p.text().trim())
                                    .filter(s -> !s.isEmpty())
                                    .collect(Collectors.joining("\n\n"));
                            break;
                        }
                    }
                    //Бонусные действия
                    String bonusActions = "";
                    Elements bActions = unitInfo.select("li.subsection.desc");
                    for (Element bAction : bActions) {
                        Element title = bAction.selectFirst("h3.subsection-title");
                        if (title != null && title.text().trim().equalsIgnoreCase("Бонусные действия")) {
                            bonusActions = bAction.select("p")
                                    .stream()
                                    .map(p -> p.text().trim())
                                    .filter(s -> !s.isEmpty())
                                    .collect(Collectors.joining("\n\n"));
                            break;
                        }
                    }
                    //Реакции
                    String reactions = "";
                    Elements reacts = unitInfo.select("li.subsection.desc");
                    for (Element react : reacts) {
                        Element title = react.selectFirst("h3.subsection-title");
                        if (title != null && title.text().trim().equalsIgnoreCase("Реакции")) {
                            reactions = react.select("p")
                                    .stream()
                                    .map(p -> p.text().trim())
                                    .filter(s -> !s.isEmpty())
                                    .collect(Collectors.joining("\n\n"));
                            break;
                        }
                    }
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", name);
                    jsonObject.put("danger", danger);
                    jsonObject.put("url", url);
                    jsonObject.put("type", type);
                    jsonObject.put("ac", ac);
                    jsonObject.put("initiative", "Инициатива " + initiative);
                    jsonObject.put("hp", hp);
                    jsonObject.put("move", move);
                    jsonObject.put("abilities", abilities);
                    jsonObject.put("skills", skills);
                    jsonObject.put("resists", resists);
                    jsonObject.put("immuns", immuns);
                    jsonObject.put("feel", feel);
                    jsonObject.put("languages", languages);
                    jsonObject.put("area", area);
                    jsonObject.put("exp", exp);
                    jsonObject.put("features",features);
                    jsonObject.put("actions", actions);
                    jsonObject.put("legendaryActions", legendaryActions);
                    jsonObject.put("bonusActions", bonusActions);
                    jsonObject.put("reactions", reactions);
                    bestiaryArray.put(jsonObject);
                    Thread.sleep(500);
                }
                FileOutputStream fos = context.openFileOutput("Bestiary5e14.json", Context.MODE_PRIVATE);
                fos.write(bestiaryArray.toString(4).getBytes());
                fos.close();
                listener.onFinish();
            } catch (Exception e) {
                listener.onError(e);
            }
        }).start();
    }

    public static void spells24Load(Context context, LoadListener listener) {
        new Thread(() -> {
            try {
                Document spells = Jsoup.connect("https://next.dnd.su/spells/").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)").referrer("https://www.google.com").timeout(10000).get();
                //Выгружаем script со страницы
                Elements scripts = spells.select("script");
                String listJson = "";
                for (Element script : scripts) {
                    String html = script.html();
                    if (html.contains("window.LIST")) {
                        listJson = html;
                        break;
                    }
                }
                //Удаляем лишний текст
                listJson = listJson.replace("window.LIST = ", "");
                listJson = listJson.substring(0, listJson.indexOf("};") + 1);
                //Создаем json строки
                JSONObject listObject = new JSONObject(listJson);
                //Получаем массив
                JSONArray cards = listObject.getJSONArray("cards");
                JSONArray spellsArray = new JSONArray();
                int total = cards.length();
                //Ищем заклинания
                for (int i = 0; i < cards.length(); i++) {
                    JSONObject card = cards.getJSONObject(i);
                    String name = card.getString("title");
                    String level = card.getString("item_prefix_title");
                    String href = card.getString("link");
                    String url = href.startsWith("http")
                            ? href
                            : "https://next.dnd.su" + href;
                    listener.onProgress(i + 1, total, name);
                    Document cardInfo = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)").referrer("https://www.google.com").timeout(10000).get();;
                    String casttime = cardInfo.select("li.cast_time").text();
                    String range = cardInfo.select("li.range").text();
                    String components = cardInfo.select("li.components").text();
                    String duration = cardInfo.select("li.duration").text();
                    String classes = cardInfo.select("li.class").text();
                    String subclasses = cardInfo.select("li.subclass").text();
                    String description = cardInfo.select("[itemprop=description]").text();
                    String lvlup = cardInfo.select("[itemprop=spell__higher-levels]").text();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", name);
                    jsonObject.put("level", level);
                    jsonObject.put("url", url);
                    jsonObject.put("casttime", casttime);
                    jsonObject.put("range", range);
                    jsonObject.put("components", components);
                    jsonObject.put("duration", duration);
                    jsonObject.put("classes", classes);
                    jsonObject.put("subclasses", subclasses);
                    jsonObject.put("description", description);
                    jsonObject.put("lvlup", lvlup);
                    spellsArray.put(jsonObject);
                    Thread.sleep(500);
                }
                FileOutputStream fos = context.openFileOutput("Spells5e24.json", Context.MODE_PRIVATE);
                fos.write(spellsArray.toString(4).getBytes());
                fos.close();
                listener.onFinish();
            } catch (Exception e) {
                listener.onError(e);
            }
        }).start();
    }

    public static void spells14Load(Context context, LoadListener listener) {
        new Thread(() -> {
            try {
                Document spells = Jsoup.connect("https://5e14.dnd.su/piece/spells/index-list/").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)").referrer("https://www.google.com").timeout(10000).get();
                //Выгружаем script со страницы
                Elements scripts = spells.select("script");
                String listJson = null;
                for (Element script : scripts) {
                    String html = script.html();
                    if (html.contains("window.LIST")) {
                        listJson = html.replace("window.LIST = ", "").trim();
                        break;
                    }
                }
                //Создаем json строки
                JSONObject listObject = new JSONObject(listJson);
                //Получаем массив
                JSONArray cards = listObject.getJSONArray("cards");
                JSONArray spellsArray = new JSONArray();
                int total = cards.length();
                //Ищем заклинания
                for (int i = 0; i < cards.length(); i++) {
                    JSONObject card = cards.getJSONObject(i);
                    String name = card.getString("title");
                    String level = card.getString("item_prefix_title");
                    String href = card.getString("link");
                    String url = href.startsWith("http")
                            ? href
                            : "https://5e14.dnd.su" + href;
                    listener.onProgress(i + 1, total, name);
                    Document cardInfo = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)").referrer("https://www.google.com").timeout(10000).get();;
                    String casttime = getParam(cardInfo, "Время накладывания");
                    String range = getParam(cardInfo, "Дистанция");
                    String components = getParam(cardInfo, "Компоненты");
                    String duration = getParam(cardInfo, "Длительность");
                    String classes = getParam(cardInfo, "Классы");
                    String subclasses = getParam(cardInfo, "Подклассы");
                    StringBuilder descBuilder = new StringBuilder();
                    Elements params = cardInfo.select("[itemprop=description] p");
                    for (Element p : params) {
                        if (!p.text().contains("На больших уровнях")) {
                            descBuilder.append(p.text()).append("\n");
                        }
                    }
                    String description = descBuilder.toString().trim();
                    String lvlup = cardInfo.select("[itemprop=description] p:contains(На больших уровнях)").text();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", name);
                    jsonObject.put("level", level);
                    jsonObject.put("url", url);
                    jsonObject.put("casttime", casttime);
                    jsonObject.put("range", range);
                    jsonObject.put("components", components);
                    jsonObject.put("duration", duration);
                    jsonObject.put("classes", classes);
                    jsonObject.put("subclasses", subclasses);
                    jsonObject.put("description", description);
                    jsonObject.put("lvlup", lvlup);
                    spellsArray.put(jsonObject);
                    Thread.sleep(500);
                    System.out.println(cardInfo.html());
                }
                FileOutputStream fos = context.openFileOutput("Spells5e14.json", Context.MODE_PRIVATE);
                fos.write(spellsArray.toString(4).getBytes());
                fos.close();
                listener.onFinish();
            } catch (Exception e) {
                e.printStackTrace();
                listener.onError(e);
            }
        }).start();
    }

    public static void items24Load(Context context, LoadListener listener) {
        new Thread(() -> {
            try {
                Document items = Jsoup.connect("https://next.dnd.su/items/")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                        .referrer("https://www.google.com")
                        .timeout(10000)
                        .get();
                Elements scripts = items.select("script");
                String listJson = null;
                for (Element script : scripts) {
                    String html = script.html();
                    if ( html.contains("window.LIST")) {
                        listJson = html;
                        break;
                    }
                }
                listJson = listJson.replace("window.LIST = ", "");
                listJson = listJson.substring(0, listJson.indexOf("};") + 1);
                JSONObject listObject = new JSONObject(listJson);
                JSONArray cards = listObject.getJSONArray("cards");
                JSONArray itemsArray = new JSONArray();
                int total = cards.length();
                for (int i = 0; i < cards.length(); i++) {
                    JSONObject card = cards.getJSONObject(i);
                    String name = card.getString("title");
                    String href = card.getString("link");
                    String url = href.startsWith("http")
                            ? href
                            : "https://next.dnd.su" + href;
                    Document itemsInfo = Jsoup.connect(url)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                            .referrer("https://www.google.com")
                            .timeout(10000)
                            .get();
                    String aligment = itemsInfo.select("li.size-type-alignment").text();
                    String price = itemsInfo.select("li.price").text();
                    StringBuilder descBuilder = new StringBuilder();
                    Element descBlock = itemsInfo.selectFirst("[itemprop=description]");
                    if (descBlock != null) {
                        Elements params = descBlock.select("p");
                        if (!params.isEmpty()) {
                            for (Element p : params) {
                                String text = p.text().trim();
                                if (!text.isEmpty()) {
                                    descBuilder.append(p.text()).append("\n\n");
                                }
                            }
                        } else {
                            String text = descBlock.text().trim();
                            if (!text.isEmpty()) {
                                descBuilder.append(text);
                            }
                        }
                    }
                    String description = descBuilder.toString();
                    StringBuilder tableBuilder = new StringBuilder();
                    Elements tables = itemsInfo.select("table");
                    if (!tables.isEmpty()) {
                        for (Element table : tables) {
                            Elements rows = table.select("tr");
                            for (Element row : rows) {
                                Elements cols = row.select("td");
                                for (int j = 0; j < cols.size(); j++) {
                                    tableBuilder.append(cols.get(j).text().trim());
                                    if (j < cols.size() - 1) {
                                        tableBuilder.append(" / ");
                                    }
                                }
                                tableBuilder.append("\n");
                            }
                        }
                    }
                    String tableDescription = tableBuilder.toString();
                    listener.onProgress(i + 1, total, name);

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", name);
                    jsonObject.put("url", url);
                    jsonObject.put("aligment", aligment);
                    jsonObject.put("price", price);
                    jsonObject.put("description", description);
                    jsonObject.put("tableDescription", tableDescription);
                    itemsArray.put(jsonObject);
                    Thread.sleep(500);
                    System.out.println(itemsArray);
                }
                FileOutputStream fos = context.openFileOutput("Items5e24.json", Context.MODE_PRIVATE);
                fos.write(itemsArray.toString(4).getBytes());
                fos.close();
                listener.onFinish();
            } catch (Exception e) {
                e.printStackTrace();
                listener.onError(e);
            }
        }).start();
    }

    public static void items14load(Context context, LoadListener listener) {
        new Thread(() -> {
            try {
                Document items = Jsoup.connect("https://5e14.dnd.su/piece/items/index-list/")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                        .referrer("https://www.google.com")
                        .timeout(10000)
                        .get();
                Elements itemsList = items.select(".for_filter");
                JSONArray itemsArray = new JSONArray();
                int total = itemsList.size();
                for (int i = 0; i < total; i++) {
                    Element item = itemsList.get(i);
                    String name = item.select(".list-item-title").text();
                    String href = item.select("a").attr("href");
                    String url = "https://5e14.dnd.su" + href;
                    listener.onProgress(i + 1, total, name);
                    Document itemsInfo = Jsoup.connect(url)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                            .referrer("https://www.google.com")
                            .timeout(10000)
                            .get();
                    String aligment = itemsInfo.select("li.size-type-alignment").text();
                    String price = itemsInfo.select("li.price").text();
                    StringBuilder descBuilder = new StringBuilder();
                    Element descBlock = itemsInfo.selectFirst("[itemprop=description]");
                    if (descBlock != null) {
                        Elements params = descBlock.select("p");
                        if (!params.isEmpty()) {
                            for (Element p : params) {
                                String text = p.text().trim();
                                if (!text.isEmpty()) {
                                    descBuilder.append(p.text()).append("\n\n");
                                }
                            }
                        } else {
                            String text = descBlock.text().trim();
                            if (!text.isEmpty()) {
                                descBuilder.append(text);
                            }
                        }
                    }
                    String description = descBuilder.toString();
                    StringBuilder tableBuilder = new StringBuilder();
                    Elements tables = itemsInfo.select("table");
                    if (!tables.isEmpty()) {
                        for (Element table : tables) {
                            Elements rows = table.select("tr");
                            for (Element row : rows) {
                                Elements cols = row.select("td");
                                for (int j = 0; j < cols.size(); j++) {
                                    tableBuilder.append(cols.get(j).text().trim());
                                    if (j < cols.size() - 1) {
                                        tableBuilder.append(" / ");
                                    }
                                }
                                tableBuilder.append("\n");
                            }
                        }
                    }
                    String tableDescription = tableBuilder.toString();

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", name);
                    jsonObject.put("url", url);
                    jsonObject.put("aligment", aligment);
                    jsonObject.put("price", price);
                    jsonObject.put("description", description);
                    jsonObject.put("tableDescription", tableDescription);
                    itemsArray.put(jsonObject);
                    Thread.sleep(500);
                }
                FileOutputStream fos = context.openFileOutput("Items5e14.json", Context.MODE_PRIVATE);
                fos.write(itemsArray.toString(4).getBytes());
                fos.close();
                listener.onFinish();
            } catch (Exception e) {
                e.printStackTrace();
                listener.onError(e);
            }
        }).start();
    }
}
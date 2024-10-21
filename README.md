## Описание

Для реализации сервиса по подбору никнеймов разработан генератор случайного текста:

```java
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
```
Для генерации 100 000 коротких слов используется:

```java
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
```

Пользователь может выбрать из них только те, которые соответствуют критериям «красивого» никнейма, а именно:

* сгенерированное слово является палиндромом, т. е. читается одинаково как слева направо, так и справа налево, например, **abba**;
* сгенерированное слово состоит из одной и той же буквы, например, **aaa**;
* буквы в слове идут по возрастанию: сначала все **a** (при наличии), затем все **b** (при наличии), затем все **c** и т. д. Например, **aaccc**.

Нужно подсчитать, сколько «красивых» слов встречается среди сгенерированных длиной 3, 4, 5. 
Проверка каждого критерия должна осуществляться в отдельном потоке. 

## Решение
https://github.com/MariaDikul/nickname-generation/blob/main/src/main/java/ru/netology/Main.java

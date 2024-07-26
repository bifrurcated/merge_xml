![Maven CI](https://github.com/DmitryVorotilov/merge_xml/actions/workflows/CI.yml/badge.svg)
![JavaDoc Pages](https://github.com/DmitryVorotilov/merge_xml/actions/workflows/deploy.yml/badge.svg)

Для запуска необходимо отредактировать конфигурацию:

1. Ставим галочку Modify options -> Environment variables

![image](images/idea_open_env.png)


2. В появившемся поле нажимаем на иконку листа "Edit environment variables" в которой мы заполняем параметры подключения к нашей БД и путь до конфигурационного файла.

![image](images/idea_env_options.png)

3. Запускаем приложение

Для проверки работы приложения необходимо выполнить шаги:


1. Скопировать абсолютный путь до директории с тестовыми фикстурами, например, */home/user/project/merge_xml/src/test/java/com/vpolosov/trainee/merge_xml/test_fixtures/Ok*
2. В Postman отправить **POST** запрос на адрес **localhost:8080/xml**, в Body формат raw Text указать скопированный путь до тестовых фикстур
3. При успешном ответе получим: Total.xml was created!

![image](/images/Screenshot%20from%202024-07-08%2013-11-07.png)

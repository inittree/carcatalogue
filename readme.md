### Требования

- Java 8+
- Подключение к интернету
- Для запуска надо внести пароль для базы данных в GCarCatalogueInfo.DATABASE_PASSWORD (пароль в том же письме, в котором была отправлена ссылка)


### Известные проблемы

- Не блокируется ввод
- Нет функционала по отмене действия (если начать действие, то его нужно выполнять до конца)
- Нет валидации ввода
- Не очищается консоль (нет кроссплатформенной возможности сделать это из java)


### Спорные решения, принятые при разработке

- Изначально для этой задачи хотел использовать SQLite, но меня смутил пункт №6 (По желанию, если язык позволяет, для хранения данных можно использовать облачное хранилище.).
- Остановился на документоориентированной базе данных (MongoDB) для наибольшего соответствия пункту №5 (Код должен быть написан таким образом, чтобы расширение полей данных по автомобилю занимало как можно меньше времени). Чтобы расширить поля данных по автомобилю, нужно просто добавить новые поля в статику в CarCatalogueInfo.
- Java выбрал для максимальной кроссплатформености и "настоящего" объектно-ориентированного программирования (в настоящее время работаю на js, опыт коммерческой разработки на java отсутствует)
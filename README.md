# urlWordsCount
Web приложение.  
Учебный проект.  
Написано с использованием Spring Framework.  
Подсчитывает кол-во повторов уникальных слов на url странице.  
---
Перед запуском приложения необходимо запустить БД PostgreSQL.  
Запустить БД в докере можно так:  
`docker run --name nogen-pg-test-13.3 -p 5432:5432 -e POSTGRES_USER=testuser -e POSTGRES_PASSWORD=testpass -e POSTGRES_DB=pgtest -d postgres:13.3`

После запуска интерфейс доступен: `http://localhost:8080/`




# Приложение Прогноз Погоды

Данное приложение использует два API для предоставления информации о погоде и определения местоположения.

## Особенности
- Предоставляет прогноз погоды отталкиваясь от геоопозции устройства
- Показывает минимальную и максимальную температуру на следующие 8 дней.
- Отображает влажность, "ощущаемую" температуру и текущую скорость ветра
- Фон меняется в зависимости от времени суток.

## Используемые API
1. API Прогноза Погоды: Предоставляет данные о погоде на следующие 8 дней [Ссылка на Open-Meteo](https://open-meteo.com/en/docs/dwd-api#latitude=52.52&longitude=13.41&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,wind_speed_10m&hourly=&daily=temperature_2m_max,temperature_2m_min,precipitation_sum,rain_sum,showers_sum,snowfall_sum&timezone=GMT&forecast_days=8&time_mode=time_interval)
2. API Определения Местоположения: Определяет текущее местоположение устройства [Ссылка на OpenWeatherMap API](https://openweathermap.org/api)

## Технологии
- Android Studio
- Язык программирования Kotlin
- Retrofit для вызовов API
- RxJava3
- ViewModel и LivaData

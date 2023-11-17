package by.clevertec;

import by.clevertec.dao.CarDAO;
import by.clevertec.dao.impl.CarDAOImpl;
import by.clevertec.db.DBRunner;
import by.clevertec.dto.CarDto;
import by.clevertec.dto.InfoCarDto;
import by.clevertec.mapper.CarMapper;
import by.clevertec.mapper.CarMapperImpl;
import by.clevertec.proxy.MyInvocationHandler;
import by.clevertec.service.CarService;
import by.clevertec.service.impl.CarServiceImpl;
import by.clevertec.util.Validation;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        System.out.println("Проверка выполнения программы");

        DBRunner.runSqlScripts();
        CarDto createCar = new CarDto("Ладушка", "Почти хороший автомобиль!", BigDecimal.valueOf(105000));
        CarDto updateCar = new CarDto("Москвич", "Был хорошим авто!", BigDecimal.valueOf(105000));

        CarDAO carDAO = new CarDAOImpl();
        CarMapper carMapper = new CarMapperImpl();

        MyInvocationHandler handler = new MyInvocationHandler(carDAO);
        CarDAO proxy = (CarDAO) Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(), new Class<?>[] {CarDAO.class}, handler);

        Validation validation = new Validation();
        validation.validate(createCar);
        CarService service = new CarServiceImpl(carMapper,proxy);
        service.create(createCar);
        List<InfoCarDto> all = service.findAll();
        System.out.println(all);
        service.findById(UUID.fromString("0b52aab1-e818-48b4-9ce5-a8c63e331b62"));
        service.update(UUID.fromString("0b52aab1-e818-48b4-9ce5-a8c63e331b62"), updateCar);
        service.delete(UUID.fromString("f9c7e367-50e3-44d3-a4c8-e6448084ba66"));
    }
}
public class Program {
    /**
     Задача 1
     ========
     Создать классы Собака и Кот с наследованием от класса Животное.
     Все животные могут бежать и плыть. В качестве параметра каждому методу передается длина препятствия.
     Результатом выполнения действия будет печать в консоль. (Например, dog1.run(150); -> 'Бобик пробежал 150 м.');
     У каждого животного есть ограничения на действия
     (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
     Добавить подсчет созданных котов, собак и животных.
     */
    public static void main(String[] args) {

        Animal[] animals = {
                new Cat("Барсик",1000, 0),
                new Dog("Шарик", 200, 300),
                new Cat("Персик", 3000, 0),
                new Dog("Тузик", 300, 100),
                new Dog("Бима", 500,4000)
        };

        for (Animal a : animals) {
            a.run(200);
            a.swim(50);
            a.run(1000);
            a.swim(30);
            a.run(1500);
            a.swim(60);
            a.run(1300);
            a.swim(70);
        }

        System.out.printf("Было создано %d котов\n", Cat.getCounter());
        System.out.printf("Было создано %d собак\n", Dog.getCounter());
        System.out.printf("Было создано всего %d животных\n", Animal.getCounter());
    }
}

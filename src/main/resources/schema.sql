DROP TABLE IF EXISTS EXERCISES;

-- Don't change anything of the Exercises table, nor fields.

CREATE TABLE EXERCISES(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    SETS_AMOUNT INT,
    REPS INT,
    WEIGHT FLOAT,
    NAME VARCHAR(255)
);
package intervals

import spock.lang.Specification

class BinaryPrinterTest extends Specification {
    void setup() {
    }

    void cleanup() {
    }

    def 'should print intervals for array of numbers'() {
        given:
        def printer = new BinaryPrinter()

        when:
        def output = printer.print numbers as int[]

        then:
        output == expected

        where:
        numbers                                              | expected
        []                                                   | ''
        [1]                                                  | '1'
        [-1]                                                 | '-1'
        [0]                                                  | '0'
        [0, 1]                                               | '0,1'
        [0, 1, 2]                                            | '0-2'
        [-4, -3, -2]                                         | '-4--2'
        [-1, 5]                                              | '-1,5'
        [5, 9]                                               | '5,9'
        [1, 2, 5]                                            | '1,2,5'
        [1, 3, 4]                                            | '1,3,4'
        [1, 2, 4, 5]                                         | '1,2,4,5'
        [1, 2, 3, 5, 7]                                      | '1-3,5,7'
        [1, 2, 3, 5, 6, 7]                                   | '1-3,5-7'
        [-1, 0, 1, 2, 5, 7, 8, 9]                            | '-1-2,5,7-9'
        [-2, -1, 0, 1, 3, 4, 6, 7, 8, 9, 11, 13, 15, 16, 17] | '-2-1,3,4,6-9,11,13,15-17'
    }
}

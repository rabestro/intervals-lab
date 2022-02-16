package intervals

import spock.lang.Specification

class X1Test extends Specification {
    void setup() {
    }

    void cleanup() {
    }

    def 'should print intervals for array of numbers'() {
        given:
        def printer = new X1()

        when:
        def output = printer.print numbers as int[]

        then:
        output == expected

        where:
        numbers      | expected
        []           | ''
        [1]          | '1'
        [-1]         | '-1'
        [0]          | '0'
        [0, 1]       | '0,1'
        [0, 1, 2]    | '0-2'
        [-4, -3, -2] | '-4--2'

    }
}

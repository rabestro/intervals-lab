package intervals

import intervals.printer.Range
import spock.lang.Specification

class RangeSpec extends Specification {
    def 'should print a range'() {
        given:
        def range = new Range(first, last)

        when:
        def output = range.print()

        then:
        output == expected

        where:
        first | last  | expected
        0     | 0     | '0'
        -1    | -1    | '-1'
        0     | 1     | '0,1'
        -1    | 0     | '-1,0'
        0     | 2     | '0-2'
        1     | 11    | '1-11'
        -11   | -1    | '-11--1'
        63875 | 63875 | '63875'
    }
}

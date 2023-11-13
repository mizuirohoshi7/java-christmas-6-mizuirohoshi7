package christmas.entity.date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class VisitDateTest {

    @Nested
    @DisplayName("방문 날짜 생성")
    class createVisitDate {

        @Test
        @DisplayName("방문 날짜 생성 성공")
        void 방문_날짜_생성_성공() {
            int day = 15;

            VisitDate visitDate = new VisitDate(day);

            assertThat(visitDate.getDay()).isEqualTo(day);
        }

        @Test
        @DisplayName("잘못된 날짜면 실패")
        void 잘못된_날짜면_실패() {
            int day = 32;

            assertThrows(IllegalArgumentException.class, () -> new VisitDate(day));
        }
    }
}
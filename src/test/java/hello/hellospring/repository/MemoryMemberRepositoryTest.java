package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    // Test가 끝날때마다 repository가 깔끔하게 지워지는 코드 -> 이유 : test는 각각 따로 동작하고 순서가 보장되지 않기 때문에 어떤 test에서 저장된 값이 다른 test에 영향을 미칠 수 있다
    // 그래서 test순서에 의존적으로 설계해서는 안된다!(어차피 test순서 보장되지않고 지맘대로의 순서로 돌림)
    @AfterEach // AfterEach : 각 테스트 메서드가 끝날때마다 실행
    public void afterEach(){
        repository.clearStore();
    }
    
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("name");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("name");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}

package movies.spring.data.neo4j.services.impl;

import movies.spring.data.neo4j.domain.Human;
import movies.spring.data.neo4j.domain.IDCard;
import movies.spring.data.neo4j.repositories.HumanRepository;
import movies.spring.data.neo4j.common.GenderTypes;
import movies.spring.data.neo4j.services.GenericService;
import movies.spring.data.neo4j.services.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Random;

/**
 * Create on 2018/7/30 17:48
 *
 * @author sunqiu@cmss.chinamobile.com
 */

@Service("humanService")
public class HumanServiceImpl extends GenericService<Human> implements HumanService {
    private final HumanRepository humanRepositiory;

    public HumanServiceImpl(HumanRepository humanRepositiory) {
        this.humanRepositiory = humanRepositiory;
    }

    @Override
    @Transactional
    public Collection<Human> findByName(String name) {
        return humanRepositiory.findByName(name);
    }

    @Override
    @Transactional
    public Collection<Human> findByIdNumber(String idNumber) {
        return humanRepositiory.getHumanByIdNumber(idNumber);
    }

    @Override
    @Transactional
    public Human createHuman(String name) {
        // todo 写到测试类中
        Human human = new Human();
        human.setName(name);
        human.setBirthday("1992-12-12");
        human.addIdCard(new IDCard("01283210381"));
        human.setGender(GenderTypes.getGenderType(0).getMessage());
        humanRepositiory.save(human);
        return human;
    }

    @Override
    public Neo4jRepository<Human, Long> getRepository() {
        return humanRepositiory;
    }
}
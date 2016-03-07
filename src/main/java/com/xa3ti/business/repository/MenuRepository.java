package com.xa3ti.business.repository;

import com.xa3ti.business.entity.Menu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MenuRepository extends
		PagingAndSortingRepository<Menu, Integer>,
		JpaSpecificationExecutor<Menu> {
    @Query("select count(*) from Menu")
    public Long queryCount();

    @Query("from Menu m where m.parId = ?1")
    public List<Menu> queryMenuByParId(int pId);

    @Query("select m from Menu m where m.keyVal = ?1")
    public Menu queryByKey(String key);

    @Query("select m from Menu m where m.flag = ?1")
    public List<Menu> queryByFlag(int flag);

    @Modifying
    @Query("delete from Menu where parId = ?1")
    public void deleteByParId(int parId);

    @Query("select count(*) from Menu m where m.parId=0")
    public Long getPCount();

    @Query("select count(*) from Menu m where m.parId=?1")
    public Long getSCount(int parId);

//    @Query("select m from Menu m where m.menuName=?1")
    public Menu findByMenuName(String menuName);

}

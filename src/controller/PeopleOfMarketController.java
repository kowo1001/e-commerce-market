package controller;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import model.dao.MartDAO;
import model.domain.Mart;
import model.util.PublicCommon;
import view.RunningEndView;

public class PeopleOfMarketController {
	 
	public static void addMart(String martName, String location) {
		EntityManager em = PublicCommon.getEntityManager();
		try {
			Mart m = MartDAO.addMart(martName, location, em);
			RunningEndView.allView(m);
		} catch (Exception e) {
			RunningEndView.showError("오류");
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void findAllMart() {
		
		EntityManager em = PublicCommon.getEntityManager();
		try {
			MartDAO.findAll(em).forEach(m -> RunningEndView.allView(m));
		} catch (NoResultException ne){
			RunningEndView.showError("마트 정보가 없습니다.");
		} catch (Exception e) {
			RunningEndView.showError("오류");
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	public static void findMart(String martName) {
		
		EntityManager em = PublicCommon.getEntityManager();
		try {
			MartDAO.findMart(martName, em).forEach(m -> RunningEndView.allView(m));
		} catch (NoResultException e) {
			RunningEndView.showError("없는 마트입니다.");
			e.printStackTrace();
		} catch (Exception e) {
			RunningEndView.showError("오류");
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void updateMart(int martNumber, String martName, String location) {
		EntityManager em = PublicCommon.getEntityManager();
		
		try {
			if (MartDAO.updateMart(martNumber, martName, location, em)) {
				RunningEndView.showMessage("수정 성공");
			} else {
				RunningEndView.showError("수정 실패");
			}
		} catch (NoResultException e) {
			RunningEndView.showError("없는 마트입니다.");
			e.printStackTrace();
		} catch (Exception e) {
			RunningEndView.showError("오류");
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void deleteMart(int martNumber) {
		EntityManager em = PublicCommon.getEntityManager();
		
		try {
			if (MartDAO.deleteMart(martNumber, em)) {
				RunningEndView.showMessage("삭제성공");
			} else {
				RunningEndView.showError("삭제실패");
			}
		} catch (NoResultException e) {
			RunningEndView.showError("없는 마트입니다.");
			e.printStackTrace();
		} catch (Exception e) {
			RunningEndView.showError("오류");
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	
	
	
	
}
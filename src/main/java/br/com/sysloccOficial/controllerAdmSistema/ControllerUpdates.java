package br.com.sysloccOficial.controllerAdmSistema;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.conf.UtilitariaMsg;


@Controller
@Transactional
public class ControllerUpdates {

	@PersistenceContext private EntityManager manager;
	@Autowired private UtilitariaMsg utilMsg;
	ModelAndView MV = new ModelAndView();
	
	@RequestMapping("/admSistema")
	public ModelAndView admSistema(HttpSession session){

	//	JOptionPane.showMessageDialog(null, "Vai invalidar a sessão.");
	//	session.invalidate();
		
		MV.setViewName("adm/admSistema");
		return MV;
	}
	
	
	@RequestMapping("/atualizaHabilitadoEmpresa")
	public ModelAndView atualizaHabilitadoEmpresa(RedirectAttributes rd){
		
		Query q = manager.createNativeQuery("UPDATE Empresa SET habilitado='-1' WHERE idEmpresa > '0' and habilitado = 1;");
		q.executeUpdate();
		
		MV.setViewName("adm/admSistema");

		rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		
		return MV;
	}
	
	
	
}

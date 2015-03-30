package br.iff.pooa20142.academia.web.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.iff.pooa20142.academia.model.controller.ModalidadePersistence;
import br.iff.pooa20142.academia.model.entity.Modalidade;

@ManagedBean(name = "modalidadeMB")
@SessionScoped
public class ModalidadeWebController {

	@EJB(lookup = "java:app/AcademiaWEB/ModalidadePersistence!br.iff.pooa20142.academia.model.controller.ModalidadePersistence")
	private ModalidadePersistence jmodalidade;
	private Modalidade modalidade;
	private Modalidade modalidadeSelecionado = new Modalidade();

	public Modalidade getModalidade() {
		if (modalidade == null) {
			modalidade = new Modalidade();
		}
		return modalidade;
	}

	public List<Modalidade> getAllModalidade() {
		return jmodalidade.findAll();
	}

	public void salva() {
		if (jmodalidade.find(modalidade.getUid()) != null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("modalidade Ja Cadastrado"));
		} else {
			jmodalidade.insert(modalidade);
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Cadastrado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("cadastro.hide()");
		;
	}

	public void delete() {
		if (jmodalidade.find(modalidadeSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("modalidade não Existe"));
		} else {
			jmodalidade.delete(modalidadeSelecionado.getUid());
		}
		RequestContext.getCurrentInstance().execute("deleta.hide()");
	}

	public Modalidade getModalidadeSelecionado() {
		return modalidadeSelecionado;
	}

	public void setModalidadeSelecionado(Modalidade modalidadeSelecionado) {
		this.modalidadeSelecionado = modalidadeSelecionado;
	}

	public void update() {
		if (jmodalidade.find(modalidadeSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("modalidade não Existe"));
		} else {
			jmodalidade.update(modalidadeSelecionado);
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Alterado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("altera.hide()");
		;
	}

	public ModalidadeWebController() {
	}

}

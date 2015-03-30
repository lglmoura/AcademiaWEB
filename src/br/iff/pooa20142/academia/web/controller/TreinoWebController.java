package br.iff.pooa20142.academia.web.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.iff.pooa20142.academia.model.controller.AlunoPersistence;
import br.iff.pooa20142.academia.model.controller.ModalidadePersistence;
import br.iff.pooa20142.academia.model.controller.TreinoPersistence;
import br.iff.pooa20142.academia.model.entity.Aluno;
import br.iff.pooa20142.academia.model.entity.Modalidade;
import br.iff.pooa20142.academia.model.entity.Treino;

@ManagedBean(name = "treinoMB")
@SessionScoped
public class TreinoWebController {

	@EJB(lookup = "java:app/AcademiaWEB/AlunoPersistence!br.iff.pooa20142.academia.model.controller.AlunoPersistence")
	private AlunoPersistence jaluno;

	@EJB(lookup = "java:app/AcademiaWEB/ModalidadePersistence!br.iff.pooa20142.academia.model.controller.ModalidadePersistence")
	private ModalidadePersistence jmodalidade;

	@EJB(lookup = "java:app/AcademiaWEB/TreinoPersistence!br.iff.pooa20142.academia.model.controller.TreinoPersistence")
	private TreinoPersistence jtreino;

	private Treino treino;
	private Treino treinoSelecionado = new Treino();

	public Treino getTreino() {
		if (treino == null) {
			treino = new Treino();
		}
		return treino;
	}

	public List<Aluno> getAllAluno() {
		return jaluno.findAll();
	}

	public List<Modalidade> getAllModalidade() {
		return jmodalidade.findAll();
	}

	public List<Treino> getAllTreino() {
		return jtreino.findAll();
	}

	public void salva() {

		if (jtreino.find(treino.getUid()) != null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("treino Ja Cadastrado"));
		} else {
			jtreino.insert(treino);
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Cadastrado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("cadastro.hide()");
		
	}

	public void delete() {
		if (jtreino.find(treinoSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("treino não Existe"));
		} else {
			jtreino.delete(treinoSelecionado.getUid());
		}
		RequestContext.getCurrentInstance().execute("deleta.hide()");
	}

	public Treino getTreinoSelecionado() {
		return treinoSelecionado;
	}

	public void setTreinoSelecionado(Treino treinoSelecionado) {
		this.treinoSelecionado = treinoSelecionado;
	}

	public void update() {
		if (jtreino.find(treinoSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("treino não Existe"));
		} else {
			jtreino.update(treinoSelecionado);
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Alterado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("altera.hide()");
		;
	}

	public TreinoWebController() {
	}

}

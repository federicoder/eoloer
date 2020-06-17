import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { PrevisioneEventoUpdateComponent } from 'app/entities/previsione-evento/previsione-evento-update.component';
import { PrevisioneEventoService } from 'app/entities/previsione-evento/previsione-evento.service';
import { PrevisioneEvento } from 'app/shared/model/previsione-evento.model';

describe('Component Tests', () => {
  describe('PrevisioneEvento Management Update Component', () => {
    let comp: PrevisioneEventoUpdateComponent;
    let fixture: ComponentFixture<PrevisioneEventoUpdateComponent>;
    let service: PrevisioneEventoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PrevisioneEventoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PrevisioneEventoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrevisioneEventoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisioneEventoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PrevisioneEvento(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PrevisioneEvento();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

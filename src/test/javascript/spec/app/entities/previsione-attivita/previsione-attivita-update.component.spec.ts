import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { PrevisioneAttivitaUpdateComponent } from 'app/entities/previsione-attivita/previsione-attivita-update.component';
import { PrevisioneAttivitaService } from 'app/entities/previsione-attivita/previsione-attivita.service';
import { PrevisioneAttivita } from 'app/shared/model/previsione-attivita.model';

describe('Component Tests', () => {
  describe('PrevisioneAttivita Management Update Component', () => {
    let comp: PrevisioneAttivitaUpdateComponent;
    let fixture: ComponentFixture<PrevisioneAttivitaUpdateComponent>;
    let service: PrevisioneAttivitaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PrevisioneAttivitaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PrevisioneAttivitaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrevisioneAttivitaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisioneAttivitaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PrevisioneAttivita(123);
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
        const entity = new PrevisioneAttivita();
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

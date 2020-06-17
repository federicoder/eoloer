import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { DatiContabiliUpdateComponent } from 'app/entities/dati-contabili/dati-contabili-update.component';
import { DatiContabiliService } from 'app/entities/dati-contabili/dati-contabili.service';
import { DatiContabili } from 'app/shared/model/dati-contabili.model';

describe('Component Tests', () => {
  describe('DatiContabili Management Update Component', () => {
    let comp: DatiContabiliUpdateComponent;
    let fixture: ComponentFixture<DatiContabiliUpdateComponent>;
    let service: DatiContabiliService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [DatiContabiliUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DatiContabiliUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DatiContabiliUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DatiContabiliService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DatiContabili(123);
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
        const entity = new DatiContabili();
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

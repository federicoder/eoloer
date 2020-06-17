import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { CondivisionePraticaUpdateComponent } from 'app/entities/condivisione-pratica/condivisione-pratica-update.component';
import { CondivisionePraticaService } from 'app/entities/condivisione-pratica/condivisione-pratica.service';
import { CondivisionePratica } from 'app/shared/model/condivisione-pratica.model';

describe('Component Tests', () => {
  describe('CondivisionePratica Management Update Component', () => {
    let comp: CondivisionePraticaUpdateComponent;
    let fixture: ComponentFixture<CondivisionePraticaUpdateComponent>;
    let service: CondivisionePraticaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [CondivisionePraticaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CondivisionePraticaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CondivisionePraticaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CondivisionePraticaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CondivisionePratica(123);
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
        const entity = new CondivisionePratica();
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

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { PersonaFisicaUpdateComponent } from 'app/entities/persona-fisica/persona-fisica-update.component';
import { PersonaFisicaService } from 'app/entities/persona-fisica/persona-fisica.service';
import { PersonaFisica } from 'app/shared/model/persona-fisica.model';

describe('Component Tests', () => {
  describe('PersonaFisica Management Update Component', () => {
    let comp: PersonaFisicaUpdateComponent;
    let fixture: ComponentFixture<PersonaFisicaUpdateComponent>;
    let service: PersonaFisicaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PersonaFisicaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PersonaFisicaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PersonaFisicaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PersonaFisicaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PersonaFisica(123);
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
        const entity = new PersonaFisica();
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

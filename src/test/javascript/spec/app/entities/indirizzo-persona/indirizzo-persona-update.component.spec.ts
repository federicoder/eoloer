import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { IndirizzoPersonaUpdateComponent } from 'app/entities/indirizzo-persona/indirizzo-persona-update.component';
import { IndirizzoPersonaService } from 'app/entities/indirizzo-persona/indirizzo-persona.service';
import { IndirizzoPersona } from 'app/shared/model/indirizzo-persona.model';

describe('Component Tests', () => {
  describe('IndirizzoPersona Management Update Component', () => {
    let comp: IndirizzoPersonaUpdateComponent;
    let fixture: ComponentFixture<IndirizzoPersonaUpdateComponent>;
    let service: IndirizzoPersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [IndirizzoPersonaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(IndirizzoPersonaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(IndirizzoPersonaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(IndirizzoPersonaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new IndirizzoPersona(123);
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
        const entity = new IndirizzoPersona();
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

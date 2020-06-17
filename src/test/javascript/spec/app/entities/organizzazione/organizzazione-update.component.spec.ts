import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { OrganizzazioneUpdateComponent } from 'app/entities/organizzazione/organizzazione-update.component';
import { OrganizzazioneService } from 'app/entities/organizzazione/organizzazione.service';
import { Organizzazione } from 'app/shared/model/organizzazione.model';

describe('Component Tests', () => {
  describe('Organizzazione Management Update Component', () => {
    let comp: OrganizzazioneUpdateComponent;
    let fixture: ComponentFixture<OrganizzazioneUpdateComponent>;
    let service: OrganizzazioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [OrganizzazioneUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OrganizzazioneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrganizzazioneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrganizzazioneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Organizzazione(123);
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
        const entity = new Organizzazione();
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
